package com.huonggiang.lab08;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestWithdraw {

    @Test
    public void testWithdrawSuccess() {
        Account acc = new Account(1000);
        boolean result = acc.withdraw(500);
        assertTrue(result);
        assertEquals(500, acc.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawFail() {
        Account acc = new Account(300);
        boolean result = acc.withdraw(500);
        assertFalse(result);
        assertEquals(300, acc.getBalance(), 0.01);
    }

    @Test
    public void testDeposit() {
        Account acc = new Account(200);
        acc.deposit(300);
        assertEquals(500, acc.getBalance(), 0.01);
    }
}