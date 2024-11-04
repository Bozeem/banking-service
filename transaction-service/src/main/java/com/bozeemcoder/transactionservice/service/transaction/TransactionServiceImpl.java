package com.bozeemcoder.transactionservice.service.transaction;
import com.bozeemcoder.transactionservice.dto.request.PostingCreateRequest;
import com.bozeemcoder.transactionservice.dto.request.TransactionCreateRequest;
import com.bozeemcoder.transactionservice.dto.response.ApiResponse;
import com.bozeemcoder.transactionservice.dto.response.TransactionResponse;
import com.bozeemcoder.transactionservice.entity.*;
import com.bozeemcoder.transactionservice.exception.DomainException;
import com.bozeemcoder.transactionservice.exception.ErrorCode;
import com.bozeemcoder.transactionservice.mapper.PostingMapper;
import com.bozeemcoder.transactionservice.mapper.TransactionMapper;
import com.bozeemcoder.transactionservice.repository.AccountRepository;
import com.bozeemcoder.transactionservice.repository.PostingRepository;
import com.bozeemcoder.transactionservice.repository.RbTranHistLogRepository;
import com.bozeemcoder.transactionservice.repository.TransactionRepository;
import com.bozeemcoder.transactionservice.service.account.AccountServiceImpl;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    RestTemplate restTemplate;

    String ACCOUNT_SERVICE_URL = "http://localhost:8080/account-service/account/read";

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;
    TransactionRepository transactionRepository;
    TransactionMapper transactionMapper;
    PostingRepository postingRepository;
    RbTranHistLogRepository rbTranHistLogRepository;
    PostingMapper postingMapper;
    AccountRepository accountRepository;

    @Override
    public List<TransactionResponse> getTransaction() {
        log.info("In method get Accounts");
        return transactionRepository.findAll().stream()
                .map(transactionMapper::toTransactionResponse)
                .toList();
    }

    @Override
    @Transactional
    public TransactionResponse createTransaction(TransactionCreateRequest request) {
        log.info("In method createTransaction");
        String toAccountId = request.getToAccount().getAccountId();
        String fromAccountId = request.getFromAccount().getAccountId();

        Optional<Account> fromAccountOpt = accountRepository.findById(fromAccountId);
        Optional<Account> toAccountOpt = accountRepository.findById(toAccountId);

        if (fromAccountOpt.isEmpty() || toAccountOpt.isEmpty()) {
            throw new DomainException(ErrorCode.ACCOUNT_NOT_EXISTED);
        }

        Account fromAccount = fromAccountOpt.get();
        Account toAccount = toAccountOpt.get();

        // Kiểm tra số dư tài khoản
        if (fromAccount.getBalance() == null || fromAccount.getBalance().getAmount().compareTo(request.getAmount()) < 0) {
            throw new DomainException(ErrorCode.BALANCE_IS_NULL);
        }
        log.info("balance {} response: {} ", fromAccountId, fromAccount.getBalance().getAmount());

        request.setFromAccount(fromAccount);
        request.setToAccount(toAccount);
        // Tạo transaction
        Transaction transaction = transactionMapper.toTransaction(request);
        // Lưu Transaction đầu tiên
        transactionRepository.save(transaction);

        // Sau khi đã lưu transaction, tạo Posting
        createPosting(transaction);
        // Tạo RbTranHistLog
        createRbTranHistLog(transaction);

        // Trả về response DTO
        return transactionMapper.toTransactionResponse(transaction);
    }

    void createPosting(Transaction transaction) {
        Posting posting = new Posting();
        posting.setTransaction(transaction); // Gán transaction đã lưu
        posting.setType(transaction.getType());
        posting.setAmount(transaction.getAmount());
        posting.setPostingDate(LocalDateTime.now());
        postingRepository.save(posting); // Lưu posting sau khi transaction đã được lưu
        PostingCreateRequest request = postingMapper.toPostingCreateRequest(posting);
        kafkaTemplate.send("transaction-service", request);
    }

    void createRbTranHistLog (Transaction transaction) {
        RbTranHistLog log = new RbTranHistLog();
        log.setTransaction(transaction);
        log.setStatus("SUCCESS");
        log.setMessage("Transaction created successfully");
        log.setTimestamp(LocalDateTime.now());
        rbTranHistLogRepository.save(log);
    }



}
