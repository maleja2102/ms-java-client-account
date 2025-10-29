package com.devsu.ms_java_account.application.service.port;

import java.util.List;

import com.devsu.ms_java_account.application.dto.AccountRequest;
import com.devsu.ms_java_account.application.dto.AccountResponse;

public interface AccountServicePort {
    List<AccountResponse> getAllAccounts();
    AccountResponse getAccountById(Long id);
    AccountResponse createAccount(AccountRequest account);
    AccountResponse updateAccount(Long id, AccountRequest account);
    void deleteAccount(Long id);
}