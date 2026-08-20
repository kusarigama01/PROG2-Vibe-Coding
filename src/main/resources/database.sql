CREATE SCHEMA IF NOT EXISTS transaction_db;

CREATE TABLE IF NOT EXISTS transaction_db.account (
                                                      id VARCHAR(255) PRIMARY KEY,
    account_type VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS transaction_db.transaction (
                                                          id VARCHAR(255) PRIMARY KEY,
    account_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                             transaction_type VARCHAR(10) NOT NULL,
    amount NUMERIC(19, 4) NOT NULL,
    reason TEXT,
    CONSTRAINT fk_transaction_account FOREIGN KEY (account_id) REFERENCES transaction_db.account(id)
    );