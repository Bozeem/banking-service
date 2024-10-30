package com.bozeemcoder.accountservice.mapper;

import com.bozeemcoder.accountservice.dto.request.AccountCreateRequest;
import com.bozeemcoder.accountservice.dto.request.AccountUpdateRequest;
import com.bozeemcoder.accountservice.dto.response.AccountResponse;
import com.bozeemcoder.accountservice.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "balance", ignore = true)
    Account toAccount(AccountCreateRequest request);

    AccountResponse toAccountResponse(Account account);

    @Mapping(target = "balance", ignore = true)
    void updateAccount(@MappingTarget Account account, AccountUpdateRequest request);

}
