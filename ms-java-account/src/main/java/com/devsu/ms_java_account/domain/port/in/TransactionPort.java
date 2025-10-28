package com.devsu.ms_java_account.domain.port.in;

import java.time.LocalDateTime;
import java.util.List;

import com.devsu.ms_java_account.domain.Transaction;

public interface TransactionPort {

    Transaction registerTransaction(Long accountId, Transaction transaction);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByAccount(Long accountId);

    List<Transaction> getTransactionsByClientAndDateRange(Long clientId, LocalDateTime start, LocalDateTime end);
}
