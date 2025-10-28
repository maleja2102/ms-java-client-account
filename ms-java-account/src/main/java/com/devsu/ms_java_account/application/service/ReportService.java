package com.devsu.ms_java_account.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.ms_java_account.application.dto.AccountReportDTO;
import com.devsu.ms_java_account.domain.Transaction;
import com.devsu.ms_java_account.domain.port.in.TransactionPort;

@Service
public class ReportService {
    private final TransactionPort transactionPort;

    public ReportService(TransactionPort transactionPort) {
        this.transactionPort = transactionPort;
    }

    public List<Transaction> getTransactionsByClientAndDateRange(Long clientId, LocalDate start, LocalDate end) {
        return transactionPort.getTransactionsByClientAndDateRange(
                clientId,
                start.atStartOfDay(),
                end.atTime(23, 59, 59));
    }

    public List<AccountReportDTO> generateReport(Long clientId, LocalDate startDate, LocalDate endDate) {
        return getTransactionsByClientAndDateRange(clientId, startDate, endDate).stream()
                .map(tx -> new AccountReportDTO(
                        tx.getDate().toLocalDate(),
                        "Client " + tx.getAccount().getClientId(),
                        tx.getAccount().getAccountNumber().toString(),
                        tx.getAccount().getAccountType().name(),
                        tx.getAccount().getInitialBalance(),
                        tx.getAccount().getActive(),
                        tx.getAmount(),
                        tx.getBalanceAfterTransaction()))
                .toList();
    }
}
