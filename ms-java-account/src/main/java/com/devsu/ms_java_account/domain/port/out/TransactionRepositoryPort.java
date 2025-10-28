package com.devsu.ms_java_account.domain.port.out;

import java.time.LocalDateTime;
import java.util.List;

import com.devsu.ms_java_account.domain.Transaction;

public interface TransactionRepositoryPort {

    Transaction save(Transaction transaction);

    List<Transaction> findAll();

    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByClientIdAndDateRange(Long clientId, LocalDateTime start, LocalDateTime end);
}
