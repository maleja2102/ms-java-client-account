package com.devsu.ms_java_account.infrastructure.repository.entity;


import java.math.BigDecimal;
import java.util.List;

import com.devsu.ms_java_account.domain.enums.AccountType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, unique = true)
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(nullable = false,precision = 15, scale= 2)
    private BigDecimal initialBalance;

    @Column(nullable = false,precision = 15, scale= 2)
    private BigDecimal currentBalance;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Long clientId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionEntity> transactions;

}
