package com.devsu.ms_java_account.aplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.domain.Transaction;
import com.devsu.ms_java_account.domain.TransactionUseCase;
import com.devsu.ms_java_account.domain.enums.AccountType;
import com.devsu.ms_java_account.domain.enums.TransactionType;
import com.devsu.ms_java_account.domain.exception.BusinessException;
import com.devsu.ms_java_account.domain.port.out.AccountRepositoryPort;
import com.devsu.ms_java_account.domain.port.out.TransactionRepositoryPort;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepositoryPort transactionRepositoryPort;

    @Mock
    private AccountRepositoryPort accountRepositoryPort;

    private TransactionUseCase transactionUseCase;
    private Account account;

    @BeforeEach
    void setUp() {
        transactionUseCase = new TransactionUseCase(transactionRepositoryPort, accountRepositoryPort);

        account = new Account();
        account.setAccountId(1L);
        account.setClientId(1L);
        account.setAccountNumber(123456L);
        account.setAccountType(AccountType.SAVINGS);
        account.setCurrentBalance(BigDecimal.valueOf(1000));
        account.setInitialBalance(BigDecimal.valueOf(1000));
        account.setActive(true);
    }

    @Test
    void registerDepositTransactionSuccessfully() {
        Transaction tx = new Transaction();
        tx.setTransactionType(TransactionType.DEPOSIT);
        tx.setAmount(BigDecimal.valueOf(200));

        when(accountRepositoryPort.findById(1L)).thenReturn(account);
        when(accountRepositoryPort.save(any(Account.class))).thenReturn(account);
        when(transactionRepositoryPort.save(any(Transaction.class))).thenAnswer(invocation -> {
            Transaction toSave = invocation.getArgument(0);
            toSave.setTransactionId(10L);
            return toSave;
        });

        Transaction saved = transactionUseCase.registerTransaction(1L, tx);

        assertThat(saved.getTransactionType()).isEqualTo(TransactionType.DEPOSIT);
        assertThat(saved.getBalanceAfterTransaction()).isEqualByComparingTo(BigDecimal.valueOf(1200));
        verify(transactionRepositoryPort, times(1)).save(any(Transaction.class));
        verify(accountRepositoryPort, times(1)).save(any(Account.class));
    }

    @Test
    void exceptionWhenInsufficientFunds() {
        Transaction tx = new Transaction();
        tx.setTransactionType(TransactionType.WITHDRAWAL);
        tx.setAmount(BigDecimal.valueOf(1500));

        when(accountRepositoryPort.findById(1L)).thenReturn(account);

        BusinessException exception = assertThrows(BusinessException.class,
                () -> transactionUseCase.registerTransaction(1L, tx));

        assertThat(exception.getMessage()).contains("Insufficient balance");
        verify(transactionRepositoryPort, never()).save(any(Transaction.class));
    }
}
