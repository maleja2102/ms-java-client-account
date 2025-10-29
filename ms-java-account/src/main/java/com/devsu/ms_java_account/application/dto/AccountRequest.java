package com.devsu.ms_java_account.application.dto;

import java.math.BigDecimal;

import com.devsu.ms_java_account.domain.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private Long accountId;
    private Long accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private Boolean active;
    private Long clientId;
}
