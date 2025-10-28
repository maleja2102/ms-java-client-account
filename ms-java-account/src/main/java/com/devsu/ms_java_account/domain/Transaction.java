package com.devsu.ms_java_account.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.devsu.ms_java_account.domain.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long transactionId;
    private LocalDateTime date;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balanceAfterTransaction;
    private Account account;

}