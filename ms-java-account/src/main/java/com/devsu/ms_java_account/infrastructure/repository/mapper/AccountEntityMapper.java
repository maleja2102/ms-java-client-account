package com.devsu.ms_java_account.infrastructure.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.infrastructure.repository.entity.AccountEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountEntityMapper {

    @Mapping(target = "transactions", ignore = true)
    AccountEntity toEntity(Account account);

    @Mapping(target = "transactions", ignore = true)
    Account toDomain(AccountEntity accountEntity);
}