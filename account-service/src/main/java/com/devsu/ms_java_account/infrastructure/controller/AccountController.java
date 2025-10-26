package com.devsu.ms_java_account.infrastructure.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devsu.ms_java_account.application.service.AccountService;
import com.devsu.ms_java_account.domain.Account;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


@Repository
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        return ResponseEntity.ok(service.createAccount(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account){
        return ResponseEntity.ok(service.updateAccount(id, account));    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
