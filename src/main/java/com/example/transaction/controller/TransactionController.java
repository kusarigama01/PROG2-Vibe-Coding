package com.example.transaction.controller;

import com.example.transaction.dto.BalanceResponseDto;
import com.example.transaction.dto.CreateTransactionRequestDto;
import com.example.transaction.model.Transaction;
import com.example.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions(@RequestParam("type") String type) {
        return transactionService.getTransactionsByType(type);
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<Transaction> getAccountTransactions(@PathVariable("id") String id) {
        return transactionService.getTransactionsByAccountId(id);
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody CreateTransactionRequestDto request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping("/accounts/{id}/balance")
    public BalanceResponseDto getAccountBalance(@PathVariable("id") String id) {
        return transactionService.getAccountBalance(id);
    }
}