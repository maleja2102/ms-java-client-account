package com.devsu.ms_java_account.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.devsu.ms_java_account.application.dto.TransactionRequest;
import com.devsu.ms_java_account.application.dto.TransactionResponse;
import com.devsu.ms_java_account.domain.Transaction;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "balanceAfterTransaction", ignore = true)
    @Mapping(target = "account", ignore = true)
    Transaction toDomain(TransactionRequest request);

    @Mapping(target = "accountId", source = "account.accountId")
    TransactionResponse toResponse(Transaction transaction);
}
