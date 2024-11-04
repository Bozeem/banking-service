package com.bozeemcoder.accountservice.service.balance;
import com.bozeemcoder.accountservice.entity.Balance;
import com.bozeemcoder.accountservice.repository.BalanceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BalanceServiceImpl implements BalanceService {
    BalanceRepository balanceRepository;
    @Override
    public void incomeBalance(String balanceId, BigDecimal amount) {
        Optional<Balance> optionalBalance = balanceRepository.findById(balanceId);
        if (optionalBalance.isPresent()) {
            Balance balance = optionalBalance.get();
            // Cộng thêm vào amount hiện tại
            balance.setAmount(balance.getAmount().add(amount));
            // Lưu lại balance đã cập nhật
            balanceRepository.save(balance);
        } else {
            throw new EntityNotFoundException("Balance không tìm thấy với ID: " + balanceId);
        }
    }

    @Override
    public void outcomeBalance(String balanceId, BigDecimal amount) {
        Optional<Balance> optionalBalance = balanceRepository.findById(balanceId);
        if (optionalBalance.isPresent()) {
            Balance balance = optionalBalance.get();
            // Kiểm tra số dư đủ để trừ
            if (balance.getAmount().compareTo(amount) >= 0) {
                // Trừ đi từ amount hiện tại
                balance.setAmount(balance.getAmount().subtract(amount));
                // Lưu lại balance đã cập nhật
                balanceRepository.save(balance);
            } else {
                throw new IllegalArgumentException("Số dư không đủ cho balance ID: " + balanceId);
            }
        } else {
            throw new EntityNotFoundException("Balance không tìm thấy với ID: " + balanceId);
        }
    }
}
