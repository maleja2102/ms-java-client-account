package com.devsu.ms_java_account.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.devsu.ms_java_account.domain.enums.AccountType;

class AccountDomainTest {

    @Test
    void createAccountWithDefaultValues(){
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber(987654L);
        account.setAccountType(AccountType.SAVINGS);
        account.setInitialBalance(BigDecimal.valueOf(1000));
        account.setCurrentBalance(account.getInitialBalance());
        account.setActive(true);
        account.setClientId(1L);

        assertThat(account.getAccountNumber()).isEqualTo(987654L);
        assertThat(account.getCurrentBalance()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(account.getAccountType()).isEqualTo(AccountType.SAVINGS);
        assertThat(account.getActive()).isTrue();
    }
    
    @Test
    void allowBalanceUpdates(){
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.valueOf(1000));
        account.setCurrentBalance(account.getCurrentBalance().add(BigDecimal.valueOf(500)));
        
        assertThat(account.getCurrentBalance()).isEqualTo(BigDecimal.valueOf(1500));
    }
}