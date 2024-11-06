package com.bozeemcoder.postingservice.service.posting;

import com.bozeemcoder.postingservice.dto.request.BalanceEvent;
import com.bozeemcoder.postingservice.dto.request.PostingCreateRequest;
import com.bozeemcoder.postingservice.entity.Posting;
import com.bozeemcoder.postingservice.repository.PostingRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostingServiceImpl implements PostingService {

    PostingRepository postingRepository;
    KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(
            topics = "transaction-service",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "postingKafkaListenerContainerFactory"
    )
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
        Posting posting = Posting.builder()
                .transaction(request.getTransaction())
                .type(request.getType())
                .amount(request.getAmount())
                .postingDate(request.getPostingDate())
                .build();

        posting = postingRepository.save(posting);
        sendBalanceUpdateEvent(posting);
    }

    @Override
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

        kafkaTemplate.send("balance-updates", outcomeEvent);
        kafkaTemplate.send("balance-updates", incomeEvent);

        log.info("Sent balance update events for posting: {}", posting.getPostingId());
    }
}