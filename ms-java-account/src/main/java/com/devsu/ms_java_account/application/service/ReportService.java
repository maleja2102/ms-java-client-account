package com.devsu.ms_java_account.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.ms_java_account.application.dto.AccountReportDTO;
import com.devsu.ms_java_account.infrastructure.repository.TransactionRepository;
import com.devsu.ms_java_account.infrastructure.repository.entity.TransactionEntity;

@Service
public class ReportService {
    private final TransactionRepository transactionRepository;

    public ReportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionEntity> getTransactionsByClientAndDateRange(Long clientId, LocalDate start, LocalDate end) {
        return transactionRepository.findByAccount_ClienteIdAndDateBetween(
                clientId,
                start.atStartOfDay(),
                end.atTime(23, 59, 59));
    }

    public List<AccountReportDTO> generateReport(Long clientId, LocalDate startDate, LocalDate endDate){
        List<TransactionEntity> transactions = getTransactionsByClientAndDateRange(clientId, startDate, endDate);
         return transactions.stream()
            .map(tx -> new AccountReportDTO(
                    tx.getDate().toLocalDate(),
                    "Client " + tx.getAccount().getClientId(),
                    tx.getAccount().getAccountNumber().toString(),
                    tx.getAccount().getAccountType().name(),
                    tx.getAccount().getInitialBalance(),
                    tx.getAccount().getActive(),
                    tx.getAmount(),
                    tx.getBalanceAfterTransaction()
            ))
            .toList();
    }

}