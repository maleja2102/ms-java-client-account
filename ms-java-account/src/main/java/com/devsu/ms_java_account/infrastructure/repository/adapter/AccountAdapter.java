package com.devsu.ms_java_account.infrastructure.repository.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.domain.exception.BusinessException;
import com.devsu.ms_java_account.domain.port.out.AccountRepositoryPort;
import com.devsu.ms_java_account.infrastructure.repository.AccountRepository;
import com.devsu.ms_java_account.infrastructure.repository.mapper.AccountEntityMapper;

@Component
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
        return accountRepository.findById(id)
                .map(accountEntityMapper::toDomain)
                .orElseThrow(() -> new BusinessException("Account not found with id: " + id));
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
