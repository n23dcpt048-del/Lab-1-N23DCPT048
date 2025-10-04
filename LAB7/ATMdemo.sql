CREATE DATABASE IF NOT EXISTS atm_demo;
USE atm_demo;

-- Bảng accounts
CREATE TABLE IF NOT EXISTS accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    balance DOUBLE NOT NULL
);

-- Bảng cards
CREATE TABLE IF NOT EXISTS cards (
    card_no VARCHAR(20) PRIMARY KEY,
    pin_hash VARCHAR(64) NOT NULL,
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

-- Bảng transactions
CREATE TABLE IF NOT EXISTS transactions (
    tx_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    card_no VARCHAR(20),
    atm_id INT,
    tx_type VARCHAR(20),
    amount DOUBLE,
    balance_after DOUBLE,
    tx_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Dữ liệu mẫu
INSERT INTO accounts(balance) VALUES (1000.0);

-- PIN là 1234 -> hash SHA-256
INSERT INTO cards(card_no, pin_hash, account_id)
VALUES ('1234567890', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1);
