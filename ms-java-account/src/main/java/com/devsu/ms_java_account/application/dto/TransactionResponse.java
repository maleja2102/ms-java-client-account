package com.devsu.ms_java_account.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.domain.enums.TransactionType;

public class TransactionResponse {
     private Long transactionId;
    private LocalDateTime date;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balanceAfterTransaction;
    private Account account;
}