package com.devsu.ms_java_account.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devsu.ms_java_account.application.service.TransactionService;
import com.devsu.ms_java_account.infrastructure.repository.entity.TransactionEntity;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/{accountId}")
    public ResponseEntity<TransactionEntity> createTransaction(@PathVariable Long accountId, @RequestBody TransactionEntity transaction){
        return ResponseEntity.ok(service.registerTransaction(accountId, transaction));
    }

    @GetMapping
    public ResponseEntity<List<TransactionEntity>> getAllTransactions() {
        return ResponseEntity.ok(service.getAllTransactions());
    }
    
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionEntity>> getByAccount(@PathVariable Long accountId){
        return ResponseEntity.ok(service.getTransactionsByAccount(accountId));
    }

}
