
CREATE SCHEMA IF NOT EXISTS transaction_db;
SET search_path TO transaction_db;

CREATE TABLE IF NOT EXISTS accounts (
                                        id VARCHAR(100) PRIMARY KEY,
    account_type VARCHAR(20) NOT NULL,
    CONSTRAINT chk_accounts_account_type
    CHECK (account_type IN ('STANDARD', 'PREMIUM', 'GOLD'))
    );

CREATE TABLE IF NOT EXISTS transactions (
                                            id VARCHAR(100) PRIMARY KEY,
    account_id VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    transaction_type VARCHAR(10) NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    reason VARCHAR(255) NOT NULL,

    CONSTRAINT fk_transactions_account
    FOREIGN KEY (account_id)
    REFERENCES accounts(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,

    CONSTRAINT chk_transactions_type
    CHECK (transaction_type IN ('IN', 'OUT')),

    CONSTRAINT chk_transactions_amount
    CHECK (amount > 0)
    );

CREATE INDEX IF NOT EXISTS idx_transactions_account_id
    ON transactions(account_id);

CREATE INDEX IF NOT EXISTS idx_transactions_type
    ON transactions(transaction_type);

CREATE INDEX IF NOT EXISTS idx_transactions_created_at
    ON transactions(created_at DESC);
