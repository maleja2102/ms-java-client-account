package com.devsu.ms_java_account.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.devsu.ms_java_account.domain.enums.TransactionType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balanceAfterTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id", nullable = false)
    private Account account;


}
