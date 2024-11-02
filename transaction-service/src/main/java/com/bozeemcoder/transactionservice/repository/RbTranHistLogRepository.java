package com.bozeemcoder.transactionservice.repository;

import com.bozeemcoder.transactionservice.entity.RbTranHistLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RbTranHistLogRepository extends JpaRepository<RbTranHistLog, String> {
}
