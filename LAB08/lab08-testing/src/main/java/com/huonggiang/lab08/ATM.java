package com.huonggiang.lab08;

public class ATM {
    private String correctPin;
    private double balance;

    public ATM(String correctPin, double balance) {
        this.correctPin = correctPin;
        this.balance = balance;
    }

    public boolean verifyPin(String pin) {
        return correctPin.equals(pin);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}