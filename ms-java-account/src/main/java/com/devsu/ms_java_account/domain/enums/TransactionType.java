package com.devsu.ms_java_account.domain.enums;

public enum TransactionType {
    DEPOSIT,
    WITHDRAWAL;

    public boolean isCredit() {
        return this == DEPOSIT;
    }
}