package com.example.transaction.dto;

import com.example.transaction.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequestDto {
    private String accountId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String reason;
}