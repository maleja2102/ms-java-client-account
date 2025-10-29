package com.devsu.ms_java_account.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.ms_java_account.application.dto.AccountRequest;
import com.devsu.ms_java_account.application.dto.AccountResponse;
import com.devsu.ms_java_account.application.mapper.AccountMapper;
import com.devsu.ms_java_account.application.service.port.AccountServicePort;
import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.domain.port.in.AccountPort;

@Service
public class AccountService implements AccountServicePort {

    private final AccountPort accountUseCase;
    private final AccountMapper accountMapper;

    public AccountService(AccountPort accountUseCase, AccountMapper accountMapper) {
        this.accountUseCase = accountUseCase;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        return accountUseCase.getAllAccounts().stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    public AccountResponse getAccountById(Long id) {
        Account account = accountUseCase.getAccountById(id);
        return accountMapper.toResponse(account);
    }

    @Override
    public AccountResponse createAccount(AccountRequest account) {
        Account createdAccount = accountUseCase.createAccount(accountMapper.toDomain(account));
        return accountMapper.toResponse(createdAccount);
    }

    @Override
    public AccountResponse updateAccount(Long id, AccountRequest account) {
        Account updatedAccount = accountUseCase.updateAccount(id, accountMapper.toDomain(account));
        return accountMapper.toResponse(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        accountUseCase.deleteAccount(id);
    }
}
