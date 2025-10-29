package com.devsu.ms_java_account.application.mapper;

import com.devsu.ms_java_account.application.dto.AccountRequest;
import com.devsu.ms_java_account.application.dto.AccountResponse;
import com.devsu.ms_java_account.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    Account toDomain(AccountRequest request);
    AccountResponse toResponse(Account account);
}