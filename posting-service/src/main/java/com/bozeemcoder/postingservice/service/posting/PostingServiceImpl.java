package com.bozeemcoder.postingservice.service.posting;

import com.bozeemcoder.postingservice.dto.request.BalanceEvent;
import com.bozeemcoder.postingservice.dto.request.PostingCreateRequest;
import com.bozeemcoder.postingservice.dto.response.ApiResponse;
import com.bozeemcoder.postingservice.entity.Posting;
import com.bozeemcoder.postingservice.entity.Transaction;
import com.bozeemcoder.postingservice.exception.DomainException;
import com.bozeemcoder.postingservice.exception.ErrorCode;
import com.bozeemcoder.postingservice.repository.PostingRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostingServiceImpl implements PostingService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    String TRANSACTION_SERVICE_URL = "http://localhost:8081/transaction-service/transaction/read";
    PostingRepository postingRepository;
    KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(id = "banking-service",topics = "transaction-service" )
    @Override
    public void processPostingEvent(PostingCreateRequest request) {
        log.info("Received posting event: {}", request);
        try {
            createPosting(request);
        } catch (Exception e) {
            log.error("Error processing posting event: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void createPosting(PostingCreateRequest request) {
        logger.info("START... posting request: {}", request);
        Posting posting = Posting.builder()
                .transaction(retrieveTransaction(request.getTransactionId()))
                .type(request.getType())
                .amount(request.getAmount())
                .postingDate(request.getPostingDate())
                .build();
        sendBalanceUpdateEvent(posting);
        logger.info("END... Posting service successfully!");
    }

    @Override
    @Async
    public void sendBalanceUpdateEvent(Posting posting) {
        String fromBalanceId = posting.getTransaction().getFromAccount().getBalance().getBalanceId();
        String toBalanceId = posting.getTransaction().getToAccount().getBalance().getBalanceId();

        // Send OUTCOME event for source account
        BalanceEvent outcomeEvent = BalanceEvent.builder()
                .balanceId(fromBalanceId)
                .amount(posting.getAmount())
                .eventType("OUTCOME")
                .timestamp(LocalDateTime.now())
                .build();

        // Send INCOME event for destination account
        BalanceEvent incomeEvent = BalanceEvent.builder()
                .balanceId(toBalanceId)
                .amount(posting.getAmount())
                .eventType("INCOME")
                .timestamp(LocalDateTime.now())
                .build();

        kafkaTemplate.send("balance-subtract", outcomeEvent);
        kafkaTemplate.send("balance-add", incomeEvent);

        logger.info("Sent balance update events for posting");
    }
    private Transaction retrieveTransaction(String transactionId) {
        return getTransaction(transactionId, restTemplate, TRANSACTION_SERVICE_URL);
    }

    public static Transaction getTransaction(String accountId, RestTemplate restTemplate, String transactionServiceUrl) {
        ApiResponse<Transaction> response = restTemplate.exchange(
                transactionServiceUrl + "/" + accountId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Transaction>>() {}
        ).getBody();

        if (response == null || response.getCode() != 0) {
            throw new DomainException(ErrorCode.FAILED_TO_RETRIEVE);
        }
        return response.getResult();
    }
}