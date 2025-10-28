package com.devsu.ms_java_account.aplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devsu.ms_java_account.application.service.TransactionService;
import com.devsu.ms_java_account.domain.enums.AccountType;
import com.devsu.ms_java_account.domain.enums.TransactionType;
import com.devsu.ms_java_account.infrastructure.repository.AccountRepository;
import com.devsu.ms_java_account.infrastructure.repository.TransactionRepository;
import com.devsu.ms_java_account.infrastructure.repository.entity.AccountEntity;
import com.devsu.ms_java_account.infrastructure.repository.entity.TransactionEntity;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService  transactionService;

    private AccountEntity account;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        account = new AccountEntity();
        account.setAccountId(1L);
        account.setClientId(1L);
        account.setAccountNumber(123456L);
        account.setAccountType(AccountType.SAVINGS);
        account.setCurrentBalance(BigDecimal.valueOf(1000));
        account.setInitialBalance(BigDecimal.valueOf(1000));
        account.setActive(true);
    }

    @Test
    void registerDepositTransactionSuccessfully()
    {
        TransactionEntity tx = new TransactionEntity();
        tx.setTransactionType(TransactionType.DEPOSIT);
        tx.setAmount(BigDecimal.valueOf(200));

        when(accountRepository.findById(1L)).thenReturn(java.util.Optional.of(account));
        when(transactionRepository.save(any(TransactionEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(account);

        TransactionEntity saved = transactionService.registerTransaction(1L, tx);

        assertThat(saved.getTransactionType()).isEqualTo(TransactionType.DEPOSIT);
        assertThat(saved.getBalanceAfterTransaction().equals(BigDecimal.valueOf(1200)));
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
        verify(accountRepository, times(1)).save(any(AccountEntity.class));

    }

    @Test
    void exceptionWhenInsufficientFunds(){
        TransactionEntity tx = new TransactionEntity();
        tx.setTransactionType(TransactionType.WITHDRAWAL);
        tx.setAmount(BigDecimal.valueOf(1500));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> transactionService.registerTransaction(1L, tx));

        assertThat(exception.getMessage().contains("Insufficient balance"));
        verify(transactionRepository, never()).save(any(TransactionEntity.class));
    }
}
