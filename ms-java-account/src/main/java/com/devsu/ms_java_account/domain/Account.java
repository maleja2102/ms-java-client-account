package com.devsu.ms_java_account.domain;

import java.math.BigDecimal;
import java.util.List;

import com.devsu.ms_java_account.domain.enums.AccountType;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Account {
    private Long accountId;
    private Long accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private Boolean active;
    private Long clientId;
    private List<Transaction> transactions;
}