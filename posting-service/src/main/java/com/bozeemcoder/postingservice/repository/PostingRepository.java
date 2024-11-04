package com.bozeemcoder.postingservice.repository;


import com.bozeemcoder.postingservice.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, String> {
}
