package com.devsu.ms_java_account.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.ms_java_account.infrastructure.repository.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    
    List<TransactionEntity> findByAccount_AccountId(Long accountId);
    List<TransactionEntity> findByAccount_ClientIdAndDateBetween(Long clientId, LocalDateTime start, LocalDateTime end);
}