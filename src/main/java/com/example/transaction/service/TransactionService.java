package com.example.transaction.service;

import com.example.transaction.dto.BalanceResponseDto;
import com.example.transaction.dto.CreateTransactionRequestDto;
import com.example.transaction.model.Transaction;
import com.example.transaction.model.TransactionType;
import com.example.transaction.repository.AccountRepository;
import com.example.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getTransactionsByType(String type) {
        TransactionType transactionType = TransactionType.valueOf(type.toUpperCase());
        return transactionRepository.findByType(transactionType);
    }

    public List<Transaction> getTransactionsByAccountId(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction createTransaction(CreateTransactionRequestDto request) {
        accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable : " + request.getAccountId()));

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .accountId(request.getAccountId())
                .createdAt(Instant.now())
                .transactionType(request.getTransactionType())
                .amount(request.getAmount())
                .reason(request.getReason())
                .build();

        return transactionRepository.save(transaction);
    }

    public BalanceResponseDto getAccountBalance(String accountId) {
        accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable : " + accountId));

        BigDecimal balance = transactionRepository.calculateBalanceByAccountId(accountId);
        return BalanceResponseDto.builder()
                .accountId(accountId)
                .balance(balance)
                .build();
    }
}