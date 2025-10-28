package com.devsu.ms_java_account.application.dto;

import java.math.BigDecimal;
import java.util.List;

import com.devsu.ms_java_account.domain.enums.AccountType;

public class AccountRequest {
    private Long accountId;
    private Long accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private Boolean active;
    private Long clientId;
    private List<TransactionResponse> transactions;
}
