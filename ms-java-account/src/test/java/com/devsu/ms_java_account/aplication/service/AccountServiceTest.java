package com.devsu.ms_java_account.aplication.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;

import com.devsu.ms_java_account.application.service.AccountService;
import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.domain.enums.AccountType;
import com.devsu.ms_java_account.infrastructure.repository.AccountRepository;



class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber(987654L);
        account.setAccountType(AccountType.SAVINGS);
        account.setInitialBalance(BigDecimal.valueOf(1000));
        account.setCurrentBalance(BigDecimal.valueOf(1000));
        account.setActive(true);
        account.setClientId(1L);
    }

    @Test
    void createAccountWithActiveStatusAndInitialBalance(){
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account saved = accountService.createAccount(account);
        assertThat(saved.getCurrentBalance().equals(BigDecimal.valueOf(1000)));
        assertThat(saved.getActive()).isTrue();
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void retrieveAccountById(){
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Account result = accountService.getAccountById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getAccountNumber().equals(987654L));
        verify(accountRepository).findById(1L);
    } 
}
