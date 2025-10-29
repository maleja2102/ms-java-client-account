package com.devsu.ms_java_account.domain.port.out;

import java.util.List;

import com.devsu.ms_java_account.domain.Account;

public interface AccountPort {

    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    public Account createAccount(Account account);
    Account updateAccount(Long id, Account updated);
    void deleteAccount(Long id);
    
}