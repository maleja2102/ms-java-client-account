package com.devsu.ms_java_account.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.ms_java_account.domain.exception.BusinessException;
import com.devsu.ms_java_account.domain.port.in.TransactionPort;
import com.devsu.ms_java_account.domain.port.out.AccountRepositoryPort;
import com.devsu.ms_java_account.domain.port.out.TransactionRepositoryPort;

@Service
public class TransactionUseCase implements TransactionPort {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;

    public TransactionUseCase(TransactionRepositoryPort transactionRepositoryPort,
            AccountRepositoryPort accountRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Transaction registerTransaction(Long accountId, Transaction transaction) {
        Account account = accountRepositoryPort.findById(accountId);
        if (transaction.getAmount() == null || transaction.getTransactionType() == null) {
            throw new BusinessException("Transaction type and amount are required");
        }
        BigDecimal newBalance = calculateNewBalance(account, transaction);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Insufficient balance");
        }

        transaction.setDate(LocalDateTime.now());
        transaction.setAccount(account);
        transaction.setBalanceAfterTransaction(newBalance);

        account.setCurrentBalance(newBalance);
        accountRepositoryPort.save(account);

        return transactionRepositoryPort.save(transaction);
    }

    private BigDecimal calculateNewBalance(Account account, Transaction transaction) {
        BigDecimal current = account.getCurrentBalance();
        BigDecimal amount = transaction.getAmount();

        return transaction.getTransactionType().isCredit()
                ? current.add(amount)
                : current.subtract(amount);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepositoryPort.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepositoryPort.findByAccountId(accountId);
    }

    @Override
    public List<Transaction> getTransactionsByClientAndDateRange(Long clientId, LocalDateTime start, LocalDateTime end) {
        return transactionRepositoryPort.findByClientIdAndDateRange(clientId, start, end);
    }
}
