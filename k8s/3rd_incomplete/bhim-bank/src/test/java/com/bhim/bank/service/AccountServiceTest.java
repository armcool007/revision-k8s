package com.bhim.bank.service;

import com.bhim.bank.domain.Account;
import com.bhim.bank.domain.AccountType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    @Test
    void depositAndWithdrawWork() {
        AccountService service = new AccountService();
        Account acc = service.create("cust1", AccountType.SAVINGS, "INR", new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), acc.getBalance());

        service.deposit(acc.getId(), new BigDecimal("50.00"));
        assertEquals(new BigDecimal("150.00"), acc.getBalance());

        service.withdraw(acc.getId(), new BigDecimal("25.00"));
        assertEquals(new BigDecimal("125.00"), acc.getBalance());
    }

    @Test
    void transferMovesFundsBetweenAccounts() {
        AccountService service = new AccountService();
        Account a = service.create("c1", AccountType.SAVINGS, "INR", new BigDecimal("200.00"));
        Account b = service.create("c2", AccountType.SAVINGS, "INR", new BigDecimal("10.00"));

        service.transfer(a.getId(), b.getId(), new BigDecimal("50.00"));

        assertEquals(new BigDecimal("150.00"), a.getBalance());
        assertEquals(new BigDecimal("60.00"), b.getBalance());
    }
}
