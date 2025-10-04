package com.huonggiang.lab08;

public class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean verifyWithdrawal(double amount) {
        return amount <= balance;
    }

    public boolean withdraw(double amount) {
        if (verifyWithdrawal(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}