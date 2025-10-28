package com.devsu.ms_java_account.infrastructure.repository.adapter;

import java.util.List;

import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.domain.port.in.AccountRepositoryPort;
import com.devsu.ms_java_account.infrastructure.repository.AccountRepository;
import com.devsu.ms_java_account.infrastructure.repository.mapper.AccountEntityMapper;

public class AccountAdapter implements AccountRepositoryPort {

    private final AccountRepository accountRepository;
    private final AccountEntityMapper accountEntityMapper;

    public AccountAdapter(AccountRepository accountRepository, AccountEntityMapper accountEntityMapper) {
        this.accountRepository = accountRepository;
        this.accountEntityMapper = accountEntityMapper;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll().stream()
                .map(accountEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).stream()
                .map(accountEntityMapper::toDomain)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public Account save(Account account) {
        var accountEntity = accountEntityMapper.toEntity(account);
        return accountEntityMapper.toDomain(accountRepository.save(accountEntity));
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
    
}
