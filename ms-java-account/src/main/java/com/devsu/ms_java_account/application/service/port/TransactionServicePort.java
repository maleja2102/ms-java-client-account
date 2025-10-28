package com.devsu.ms_java_account.application.service.port;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.devsu.ms_java_account.domain.enums.TransactionType;
import com.devsu.ms_java_account.infrastructure.repository.entity.AccountEntity;
import com.devsu.ms_java_account.infrastructure.repository.entity.TransactionEntity;

import jakarta.transaction.Transactional;

public class TransactionServicePort {
    
    TransactionEntity registerTransaction(Long accountId, TransactionEntity transaction);
    BigDecimal calculateNewBalance(AccountEntity account, TransactionEntity transaction);
    List<TransactionEntity> getAllTransactions();
    List<TransactionEntity> getTransactionsByAccount(Long accountId);

}
