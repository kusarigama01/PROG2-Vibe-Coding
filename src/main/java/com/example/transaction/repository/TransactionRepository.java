package com.example.transaction.repository;

import com.example.transaction.model.Transaction;
import com.example.transaction.model.TransactionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Transaction> transactionRowMapper = (rs, rowNum) -> Transaction.builder()
            .id(rs.getString("id"))
            .accountId(rs.getString("account_id"))
            .createdAt(rs.getTimestamp("created_at").toInstant())
            .transactionType(TransactionType.valueOf(rs.getString("transaction_type")))
            .amount(rs.getBigDecimal("amount"))
            .reason(rs.getString("reason"))
            .build();

    public Transaction save(Transaction transaction) {
        String sql = "INSERT INTO transaction_db.transaction (id, account_id, created_at, transaction_type, amount, reason) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                transaction.getId(),
                transaction.getAccountId(),
                Timestamp.from(transaction.getCreatedAt()),
                transaction.getTransactionType().name(),
                transaction.getAmount(),
                transaction.getReason()
        );
        return transaction;
    }

    public List<Transaction> findByType(TransactionType type) {
        String sql = "SELECT id, account_id, created_at, transaction_type, amount, reason " +
                "FROM transaction_db.transaction WHERE transaction_type = ?";
        return jdbcTemplate.query(sql, transactionRowMapper, type.name());
    }

    public List<Transaction> findByAccountId(String accountId) {
        String sql = "SELECT id, account_id, created_at, transaction_type, amount, reason " +
                "FROM transaction_db.transaction WHERE account_id = ?";
        return jdbcTemplate.query(sql, transactionRowMapper, accountId);
    }

    public BigDecimal calculateBalanceByAccountId(String accountId) {
        String sql = "SELECT COALESCE(SUM(CASE WHEN transaction_type = 'IN' THEN amount ELSE -amount END), 0) " +
                "FROM transaction_db.transaction WHERE account_id = ?";
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, accountId);
        return balance != null ? balance : BigDecimal.ZERO;
    }
}