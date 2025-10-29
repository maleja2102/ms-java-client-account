package com.devsu.ms_java_account.infrastructure.controller;

import com.devsu.ms_java_account.application.service.port.AccountServicePort;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.ms_java_account.application.dto.AccountRequest;
import com.devsu.ms_java_account.application.dto.AccountResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountServicePort accountServicePort;

    public AccountController(AccountServicePort accountServicePort) {
        this.accountServicePort = accountServicePort;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAll() {
        return ResponseEntity.ok(accountServicePort.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(accountServicePort.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody AccountRequest account) {
        return ResponseEntity.ok(accountServicePort.createAccount(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable Long id, @RequestBody AccountRequest account){
        return ResponseEntity.ok(accountServicePort.updateAccount(id, account));    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        accountServicePort.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}