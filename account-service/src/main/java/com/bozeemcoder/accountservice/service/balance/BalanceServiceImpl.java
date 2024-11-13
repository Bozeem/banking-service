package com.bozeemcoder.accountservice.service.balance;
import com.bozeemcoder.accountservice.entity.Balance;
import com.bozeemcoder.accountservice.repository.BalanceRepository;
import com.bozeemcoder.postingservice.dto.request.BalanceEvent;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BalanceServiceImpl implements BalanceService {
    BalanceRepository balanceRepository;

    @KafkaListener(topics = "balance-add", groupId = "account-service-group")
    public void handleBalanceAddEvent(BalanceEvent event) {
        log.info("Received balance-add event: {}", event);
        incomeBalance(event.getBalanceId(), event.getAmount());
    }

    @KafkaListener(topics = "balance-subtract", groupId = "account-service-group")
    public void handleBalanceSubtractEvent(BalanceEvent event) {
        log.info("Received balance-subtract event: {}", event);
        outcomeBalance(event.getBalanceId(), event.getAmount());
    }

    @Override
    @Transactional
    public void incomeBalance(String balanceId, BigDecimal amount) {
        Optional<Balance> optionalBalance = balanceRepository.findById(balanceId);
        if (optionalBalance.isPresent()) {
            Balance balance = optionalBalance.get();
            balance.setAmount(balance.getAmount().add(amount));
            balanceRepository.save(balance);
            log.info("Balance increased for ID {}: new amount {}", balanceId, balance.getAmount());
        } else {
            throw new EntityNotFoundException("Balance not found: " + balanceId);
        }
    }

    @Override
    @Transactional
    public void outcomeBalance(String balanceId, BigDecimal amount) {
        Optional<Balance> optionalBalance = balanceRepository.findById(balanceId);
        if (optionalBalance.isPresent()) {
            Balance balance = optionalBalance.get();
            if (balance.getAmount().compareTo(amount) < 0) {
                throw new IllegalStateException("Insufficient balance");
            }
            balance.setAmount(balance.getAmount().subtract(amount));
            balanceRepository.save(balance);
            log.info("Balance decreased for ID {}: new amount {}", balanceId, balance.getAmount());
        } else {
            throw new EntityNotFoundException("Balance not found: " + balanceId);
        }
    }
}
