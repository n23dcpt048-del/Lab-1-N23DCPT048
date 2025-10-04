package com.huonggiang.lab08;

public class WithdrawModule {
    public static boolean processWithdraw(ATM atm, double amount) {
        return atm.withdraw(amount);
    }
}