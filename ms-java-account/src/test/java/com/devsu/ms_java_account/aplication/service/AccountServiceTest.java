package com.devsu.ms_java_account.aplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
import com.devsu.ms_java_account.domain.AccountUseCase;
import com.devsu.ms_java_account.domain.enums.AccountType;
import com.devsu.ms_java_account.domain.port.out.AccountRepositoryPort;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepositoryPort accountRepositoryPort;

    private AccountUseCase accountUseCase;
    private Account account;

    @BeforeEach
    void setUp() {
        accountUseCase = new AccountUseCase(accountRepositoryPort);
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
    void createAccountWithActiveStatusAndInitialBalance() {
        when(accountRepositoryPort.save(any(Account.class))).thenAnswer(invocation -> {
            Account toSave = invocation.getArgument(0);
            toSave.setAccountId(1L);
            return toSave;
        });

        Account saved = accountUseCase.createAccount(account);

        assertThat(saved.getCurrentBalance()).isEqualByComparingTo(BigDecimal.valueOf(1000));
        assertThat(saved.getActive()).isTrue();
        verify(accountRepositoryPort, times(1)).save(any(Account.class));
    }

    @Test
    void retrieveAccountById() {
        when(accountRepositoryPort.findById(1L)).thenReturn(account);

        Account result = accountUseCase.getAccountById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getAccountNumber()).isEqualTo(987654L);
        verify(accountRepositoryPort).findById(1L);
    }
}
