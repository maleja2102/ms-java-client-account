package com.devsu.ms_java_account.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.ms_java_account.domain.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByClientId(Long clientId);
}