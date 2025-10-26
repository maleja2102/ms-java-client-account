package com.devsu.ms_java_account.application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.ms_java_account.infrastructure.repository.AccountRepository;
import com.devsu.ms_java_account.domain.Account;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository){
        this.repository = repository;
    }

    public List<Account> getAllAccounts(){
        return repository.findAll();
    }

    public Account getAccountById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account createAccount(Account account){
        account.setCurrentBalance(account.getInitialBalance() != null ? account.getInitialBalance() : BigDecimal.ZERO);
        account.setActive(true);
        return repository.save(account);
    }

    public Account updateAccount(Long id, Account updated){
        Account existing = getAccountById(id);
        existing.setAccountType(updated.getAccountType());
        existing.setActive(updated.getActive());
        return repository.save(existing);
    }

    public void deleteAccount(Long id){
        repository.deleteById(id);
    }
}
