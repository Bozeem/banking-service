package com.bozeemcoder.postingservice.service.posting;

import com.bozeemcoder.postingservice.dto.request.PostingCreateRequest;
import com.bozeemcoder.postingservice.entity.Posting;

public interface PostingService {
    void processPostingEvent(PostingCreateRequest request);
    void createPosting(PostingCreateRequest request);
    void sendBalanceUpdateEvent(Posting posting);
}