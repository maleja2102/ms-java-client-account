package com.devsu.ms_java_account.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AccountReportDTO(
    LocalDate date,
    String clientName,
    String accountNumber,
    String accountType,
    BigDecimal initialBalance,
    Boolean active,
    BigDecimal transactionAmount,
    BigDecimal availableBalance
) {}
