package com.devsu.ms_java_account.infrastructure.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.devsu.ms_java_account.domain.Transaction;
import com.devsu.ms_java_account.infrastructure.repository.entity.TransactionEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionEntityMapper {

    @Mapping(target = "account", ignore = true)
    TransactionEntity toEntity(Transaction transaction);

    @Mapping(target = "account", ignore = true)
    Transaction toDomain(TransactionEntity transactionEntity);
}
