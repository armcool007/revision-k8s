package com.bhim.bank.service;

import com.bhim.bank.domain.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final Map<String, Account> accountsById = new ConcurrentHashMap<>();
    private final Map<String, List<Account>> accountsByCustomer = new ConcurrentHashMap<>();

    public Account create(String customerId, AccountType type, String currency, BigDecimal openingBalance) {
        String id = UUID.randomUUID().toString();
        String accountNumber = generateAccountNumber();
        Account account = new Account(id, customerId, accountNumber, type, currency, openingBalance, Instant.now());
        accountsById.put(id, account);
        accountsByCustomer.computeIfAbsent(customerId, k -> new ArrayList<>()).add(account);
        return account;
    }

    public Optional<Account> get(String id) {
        return Optional.ofNullable(accountsById.get(id));
    }

    public List<Account> listByCustomer(String customerId) {
        return new ArrayList<>(accountsByCustomer.getOrDefault(customerId, List.of()));
    }

    public void deposit(String accountId, BigDecimal amount) {
        Account account = requireAccount(accountId);
        account.deposit(amount);
    }

    public void withdraw(String accountId, BigDecimal amount) {
        Account account = requireAccount(accountId);
        account.withdraw(amount);
    }

    public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        if (fromAccountId.equals(toAccountId)) throw new IllegalArgumentException("Cannot transfer to same account");
        Account from = requireAccount(fromAccountId);
        Account to = requireAccount(toAccountId);
        from.withdraw(amount);
        to.deposit(amount);
    }

    private Account requireAccount(String id) {
        Account account = accountsById.get(id);
        if (account == null) throw new NoSuchElementException("Account not found");
        return account;
    }

    private String generateAccountNumber() {
        return "BHIM" + System.currentTimeMillis();
    }
}
