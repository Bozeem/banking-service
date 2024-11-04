package com.bozeemcoder.postingservice.mapper;


import com.bozeemcoder.postingservice.dto.request.PostingCreateRequest;
import com.bozeemcoder.postingservice.entity.Posting;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostingMapper {
    PostingCreateRequest toPostingCreateRequest(Posting posting);
}
