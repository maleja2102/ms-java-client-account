package com.devsu.ms_java_account.infrastructure.repository.mapper;

import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.infrastructure.repository.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {
    AccountEntity toEntity(Account account);
    Account toDomain(AccountEntity accountEntity);
}