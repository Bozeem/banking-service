package com.bozeemcoder.transactionservice.repository;

import com.bozeemcoder.transactionservice.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, String> {
}
