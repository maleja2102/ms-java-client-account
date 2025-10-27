package com.devsu.ms_java_account.aplication.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.devsu.ms_java_account.application.dto.AccountReportDTO;
import com.devsu.ms_java_account.application.service.ReportService;
import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.domain.Transaction;
import com.devsu.ms_java_account.domain.enums.AccountType;
import com.devsu.ms_java_account.domain.enums.TransactionType;
import com.devsu.ms_java_account.infrastructure.repository.TransactionRepository;

class ReportServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private ReportService reportService;

    private Transaction transaction;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        Account account = new Account();
        account.setAccountId(1L);
        account.setClientId(1L);
        account.setAccountNumber(123456L);
        account.setAccountType(AccountType.SAVINGS);
        account.setInitialBalance(BigDecimal.valueOf(500));
        account.setCurrentBalance(BigDecimal.valueOf(800));
        account.setActive(true);

        transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setAccount(account);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAmount(BigDecimal.valueOf(300));
        transaction.setDate(LocalDateTime.now());
        transaction.setBalanceAfterTransaction(BigDecimal.valueOf(800));
    }

    @Test
    void generateReportForClientAndDateRange(){
        LocalDate start = LocalDate.now().minusDays(5);
        LocalDate end = LocalDate.now();

        when(transactionRepository.findByAccount_ClienteIdAndDateBetween(
            1L,
            start.atStartOfDay(),
            end.atTime(23,59,59)
            )).thenReturn(List.of(transaction));

        List<AccountReportDTO> report = reportService.generateReport(1L, start, end);

        assertThat(report).hasSize(1);
        assertThat(report.get(0).accountNumber().equals(123456L));
        assertThat(report.get(0).transactionAmount().equals(BigDecimal.valueOf(300)));
    }

}
