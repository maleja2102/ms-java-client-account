package com.devsu.ms_java_account.infrastructure.repository.adapter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.devsu.ms_java_account.domain.Transaction;
import com.devsu.ms_java_account.domain.exception.BusinessException;
import com.devsu.ms_java_account.domain.port.out.TransactionRepositoryPort;
import com.devsu.ms_java_account.infrastructure.repository.AccountRepository;
import com.devsu.ms_java_account.infrastructure.repository.TransactionRepository;
import com.devsu.ms_java_account.infrastructure.repository.entity.AccountEntity;
import com.devsu.ms_java_account.infrastructure.repository.entity.TransactionEntity;
import com.devsu.ms_java_account.infrastructure.repository.mapper.AccountEntityMapper;
import com.devsu.ms_java_account.infrastructure.repository.mapper.TransactionEntityMapper;

@Component
public class TransactionAdapter implements TransactionRepositoryPort {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionEntityMapper transactionEntityMapper;
    private final AccountEntityMapper accountEntityMapper;

    public TransactionAdapter(TransactionRepository transactionRepository,
            AccountRepository accountRepository,
            TransactionEntityMapper transactionEntityMapper,
            AccountEntityMapper accountEntityMapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionEntityMapper = transactionEntityMapper;
        this.accountEntityMapper = accountEntityMapper;
    }

    @Override
    public Transaction save(Transaction transaction) {
        AccountEntity accountEntity = accountRepository.findById(transaction.getAccount().getAccountId())
                .orElseThrow(() -> new BusinessException(
                        "Account not found with id: " + transaction.getAccount().getAccountId()));

        TransactionEntity entity = transactionEntityMapper.toEntity(transaction);
        entity.setAccount(accountEntity);

        TransactionEntity saved = transactionRepository.save(entity);
        Transaction savedTransaction = transactionEntityMapper.toDomain(saved);
        savedTransaction.setAccount(accountEntityMapper.toDomain(saved.getAccount()));
        return savedTransaction;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll().stream()
                .map(this::mapWithAccount)
                .toList();
    }

    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        return transactionRepository.findByAccount_AccountId(accountId).stream()
                .map(this::mapWithAccount)
                .toList();
    }

    @Override
    public List<Transaction> findByClientIdAndDateRange(Long clientId, LocalDateTime start, LocalDateTime end) {
        return transactionRepository.findByAccount_ClientIdAndDateBetween(clientId, start, end).stream()
                .map(this::mapWithAccount)
                .toList();
    }

    private Transaction mapWithAccount(TransactionEntity entity) {
        Transaction transaction = transactionEntityMapper.toDomain(entity);
        if (entity.getAccount() != null) {
            transaction.setAccount(accountEntityMapper.toDomain(entity.getAccount()));
        }
        return transaction;
    }
}
