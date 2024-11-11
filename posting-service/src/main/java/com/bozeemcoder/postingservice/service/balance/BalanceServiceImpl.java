package com.bozeemcoder.postingservice.service.balance;
import com.bozeemcoder.postingservice.dto.response.BalanceResponse;
import com.bozeemcoder.postingservice.entity.Balance;
import com.bozeemcoder.postingservice.repository.BalanceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    BalanceRepository balanceRepository;

    @Override
    @Transactional
    public BalanceResponse updateBalance(String balanceId, BalanceResponse balanceResponse) {
        log.info("Updating balance for ID: {}", balanceId);

        Balance balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new EntityNotFoundException("Balance not found: " + balanceId));

        balance.setAmount(balanceResponse.getAmount());
        Balance updatedBalance = balanceRepository.save(balance);

        return BalanceResponse.builder()
                .balanceId(updatedBalance.getBalanceId())
                .amount(updatedBalance.getAmount())
                .build();
    }
}
