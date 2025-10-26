package com.devsu.ms_java_account.application.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.domain.Transaction;
import com.devsu.ms_java_account.domain.enums.TransactionType;
import com.devsu.ms_java_account.infrastructure.repository.AccountRepository;
import com.devsu.ms_java_account.infrastructure.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction registerTransaction(Long accountId, Transaction transaction){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal newBalance = calculateNewBalance(account, transaction);

        if(newBalance.compareTo(BigDecimal.ZERO)< 0){
            throw new RuntimeException("Insufficient balance");
        }

        transaction.setDate(LocalDateTime.now());
        transaction.setBalanceAfterTransaction(newBalance);
        transaction.setAccount(account);

        account.setCurrentBalance(newBalance);
        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }

    private BigDecimal calculateNewBalance(Account account, Transaction transaction){
        BigDecimal current = account.getCurrentBalance();
        BigDecimal amount = transaction.getAmount();

        return transaction.getTransactionType() == TransactionType.DEPOSIT
        ? current.add(amount)
        : current.subtract(amount);
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByAccount(Long accountId){
        return transactionRepository.findByAccount_AccountId(accountId);
    }

}
