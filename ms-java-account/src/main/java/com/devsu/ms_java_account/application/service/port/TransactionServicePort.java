package com.devsu.ms_java_account.application.service.port;

import java.util.List;

import com.devsu.ms_java_account.application.dto.TransactionRequest;
import com.devsu.ms_java_account.application.dto.TransactionResponse;

public interface TransactionServicePort {

    TransactionResponse registerTransaction(Long accountId, TransactionRequest transactionRequest);

    List<TransactionResponse> getAllTransactions();

    List<TransactionResponse> getTransactionsByAccount(Long accountId);
}
