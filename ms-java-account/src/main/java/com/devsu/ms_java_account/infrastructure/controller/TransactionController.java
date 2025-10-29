package com.devsu.ms_java_account.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.ms_java_account.application.dto.TransactionRequest;
import com.devsu.ms_java_account.application.dto.TransactionResponse;
import com.devsu.ms_java_account.application.service.port.TransactionServicePort;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionServicePort transactionService;

    public TransactionController(TransactionServicePort transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{accountId}")
    public ResponseEntity<TransactionResponse> createTransaction(@PathVariable Long accountId,
            @RequestBody TransactionRequest transaction) {
        return ResponseEntity.ok(transactionService.registerTransaction(accountId, transaction));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionResponse>> getByAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccount(accountId));
    }
}
