package com.devsu.ms_java_account.domain.port.in;

import java.util.List;

import com.devsu.ms_java_account.domain.Account;

public interface AccountRepositoryPort {

    List<Account> findAll();
    Account findById(Long id);
    Account save(Account account);
    void deleteById(Long id);
}