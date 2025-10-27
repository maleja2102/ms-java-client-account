package com.devsu.ms_java_account.infrastructure.integration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import com.devsu.ms_java_account.domain.Account;
import com.devsu.ms_java_account.domain.Transaction;
import com.devsu.ms_java_account.infrastructure.repository.AccountRepository;
import com.devsu.ms_java_account.infrastructure.repository.TransactionRepository;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UseCaseIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @Order(1)
    void connectToMySQLAndFindPreloadedAccounts() {
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts).isNotEmpty();
        assertThat(accounts).hasSizeGreaterThanOrEqualTo(4);
        System.out.println("Connected to MySQl, found" + accounts.size() + "accounts.");
    }

    @Test
    @Order(2)
    void findTransactionsForMarianela() {
        List<Transaction> marianelaTxs = transactionRepository.findByAccount_ClienteIdAndDateBetween(2L,
                LocalDateTime.parse("2022-02-07T00:00:00"), LocalDateTime.parse("2022-02-11T00:00:00"));

        assertThat(marianelaTxs).isNotEmpty();
        assertThat(marianelaTxs).extracting(Transaction::getAmount).contains(BigDecimal.valueOf(600),
                BigDecimal.valueOf(-540));

        System.out.println("Found Marianela's transactions: " + marianelaTxs.size());
        marianelaTxs.forEach(tx -> System.out.printf("Account %d | Type: %s | Amount: %s | Balance: %s%n",
                tx.getAccount().getAccountNumber(),
                tx.getTransactionType(),
                tx.getAmount(),
                tx.getBalanceAfterTransaction()
                ));

    }

    @Test
    @Order(3)
    void validateJoseWithdrawal(){
        Account joseAccount = accountRepository.findAll().stream()
        .filter(a -> a.getAccountNumber() == 478758L)
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Account not found"));

        List<Transaction> joseTxs = transactionRepository.findByAccount_AccountId(joseAccount.getAccountId());
        assertThat(joseTxs).isNotEmpty();

        Transaction lastTx = joseTxs.get(0);
        assertThat(lastTx.getBalanceAfterTransaction()).isEqualTo(BigDecimal.valueOf(1425));

        System.out.println("💰 Jose Lema withdrawal verified. Balance after transaction: " + lastTx.getBalanceAfterTransaction());
    }

    @Test
    @Order(4)
    void verifyTransactionIntegrityAndForeignKeys(){
        List<Transaction> allTxs = transactionRepository.findAll();
        assertThat(allTxs).hasSizeGreaterThanOrEqualTo(4);
        allTxs.forEach(tx->{
            assertThat(tx.getAccount()).isNotNull();
            assertThat(tx.getBalanceAfterTransaction()).isGreaterThanOrEqualTo(BigDecimal.ZERO);
        });

        System.out.println("All transactions have valid account relationship");
    }
}