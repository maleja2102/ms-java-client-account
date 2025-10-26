package com.devsu.ms_java_account.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.ms_java_account.domain.Transaction;
import com.devsu.ms_java_account.infrastructure.repository.TransactionRepository;

@Service
public class ReportService {
    private final TransactionRepository transactionRepository;

    public ReportService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsByClientAndDateRange(Long clientId, LocalDate start, LocalDate end) {
        return transactionRepository.findByAccount_ClienteIdAndDateBetween(clientId, start.atStartOfDay(), end.atTime(23,59,59));
    }
}