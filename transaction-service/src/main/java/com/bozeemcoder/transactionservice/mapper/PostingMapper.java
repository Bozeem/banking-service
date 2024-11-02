package com.bozeemcoder.transactionservice.mapper;

import com.bozeemcoder.transactionservice.dto.request.PostingCreateRequest;
import com.bozeemcoder.transactionservice.dto.response.PostingResponse;
import com.bozeemcoder.transactionservice.entity.Posting;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostingMapper {
    PostingCreateRequest  toPostingCreateRequest(Posting posting);
}
