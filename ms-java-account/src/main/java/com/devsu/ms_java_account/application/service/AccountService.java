package com.devsu.ms_java_account.application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.ms_java_account.application.dto.AccountRequest;
import com.devsu.ms_java_account.application.dto.AccountResponse;
import com.devsu.ms_java_account.application.service.port.AccountServicePort;
import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.domain.port.out.AccountPort;
import com.devsu.ms_java_account.infrastructure.repository.AccountRepository;
import com.devsu.ms_java_account.infrastructure.repository.entity.AccountEntity;

@Service
public class AccountService implements AccountServicePort{

    private final AccountPort accountUseCase;
   
    public AccountService(AccountPort accountUseCase){
        this.accountUseCase = accountUseCase;
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountUseCase.getAllAccounts();
        return accounts.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AccountResponse getAccountById(Long id) {
        Account account = accountUseCase.getAccountById(id);
        return mapToResponse(account);
    }

    @Override
    public AccountResponse createAccount(AccountRequest account) {
        AccountEntity accountEntity = mapToEntity(account);
        Account createdAccount = accountUseCase.createAccount(accountEntity);
        return mapToResponse(createdAccount);
    }

    @Override
    public AccountResponse updateAccount(Long id, AccountRequest account) {
        AccountEntity accountEntity = mapToEntity(account);
        Account updatedAccount = accountUseCase.updateAccount(id, accountEntity);
        return mapToResponse(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        accountUseCase.deleteAccount(id);
    }

}