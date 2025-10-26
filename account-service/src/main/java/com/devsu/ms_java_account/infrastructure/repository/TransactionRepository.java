package com.devsu.ms_java_account.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.ms_java_account.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    List<Transaction> findByAccount_AccountId(Long accountId);
    List<Transaction> findByAccount_ClienteIdAndDateBetween(Long clientId, LocalDateTime start, LocalDateTime end);
}