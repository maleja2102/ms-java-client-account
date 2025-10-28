package com.devsu.ms_java_account.domain;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.ms_java_account.domain.port.in.AccountRepositoryPort;
import com.devsu.ms_java_account.domain.port.out.AccountPort;
import com.devsu.ms_java_account.infrastructure.repository.AccountRepository;
import com.devsu.ms_java_account.infrastructure.repository.entity.AccountEntity;

@Service
public class AccountUseCase  implements AccountPort{

    private final AccountRepositoryPort repositoryPort;

    public AccountUseCase(AccountRepositoryPort repositoryPort){
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<Account> getAllAccounts(){
        return repositoryPort.findAll();
    }

    @Override
    public Account getAccountById(Long id){
        return repositoryPort.findById(id);
    }

    @Override
    public Account createAccount(Account account){
        account.setCurrentBalance(account.getInitialBalance() != null ? account.getInitialBalance() : BigDecimal.ZERO);
        account.setActive(true);
        return repositoryPort.save(account);
    }

    @Override
    public Account updateAccount(Long id, Account updated){
        Account existing = getAccountById(id);
        existing.setAccountType(updated.getAccountType());
        existing.setActive(updated.getActive());
        return repositoryPort.save(existing);
    }

    @Override
    public void deleteAccount(Long id){
        repositoryPort.deleteById(id);
    }
}