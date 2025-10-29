package com.devsu.ms_java_account.application.dto;

import java.math.BigDecimal;

import com.devsu.ms_java_account.domain.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private TransactionType transactionType;
    private BigDecimal amount;
}
