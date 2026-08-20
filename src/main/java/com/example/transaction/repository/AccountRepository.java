package com.example.transaction.repository;

import com.example.transaction.model.Account;
import com.example.transaction.model.AccountType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Account> accountRowMapper = (rs, rowNum) -> Account.builder()
            .id(rs.getString("id"))
            .accountType(AccountType.valueOf(rs.getString("account_type")))
            .build();

    public Optional<Account> findById(String id) {
        String sql = "SELECT id, account_type FROM transaction_db.account WHERE id = ?";
        return jdbcTemplate.query(sql, accountRowMapper, id).stream().findFirst();
    }

    public void save(Account account) {
        String sql = "INSERT INTO transaction_db.account (id, account_type) VALUES (?, ?)";
        jdbcTemplate.update(sql, account.getId(), account.getAccountType().name());
    }
}