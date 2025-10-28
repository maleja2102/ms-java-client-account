package com.devsu.ms_java_account.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.ms_java_account.application.dto.TransactionRequest;
import com.devsu.ms_java_account.application.dto.TransactionResponse;
import com.devsu.ms_java_account.application.mapper.TransactionMapper;
import com.devsu.ms_java_account.application.service.port.TransactionServicePort;
import com.devsu.ms_java_account.domain.Transaction;
import com.devsu.ms_java_account.domain.port.in.TransactionPort;

@Service
public class TransactionService implements TransactionServicePort {

    private final TransactionPort transactionPort;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionPort transactionPort, TransactionMapper transactionMapper) {
        this.transactionPort = transactionPort;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionResponse registerTransaction(Long accountId, TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toDomain(transactionRequest);
        Transaction saved = transactionPort.registerTransaction(accountId, transaction);
        return transactionMapper.toResponse(saved);
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        return transactionPort.getAllTransactions().stream()
                .map(transactionMapper::toResponse)
                .toList();
    }

    @Override
    public List<TransactionResponse> getTransactionsByAccount(Long accountId) {
        return transactionPort.getTransactionsByAccount(accountId).stream()
                .map(transactionMapper::toResponse)
                .toList();
    }
}
