package com.devsu.ms_java_account.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.devsu.ms_java_account.domain.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long transactionId;
    private LocalDateTime date;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balanceAfterTransaction;
    private Long accountId;
}
