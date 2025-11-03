package com.bhim.bank.service;

import com.bhim.bank.domain.Transaction;
import com.bhim.bank.domain.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final Map<String, List<Transaction>> transactionsByAccount = new ConcurrentHashMap<>();

    public Transaction record(String accountId, TransactionType type, BigDecimal amount, String description) {
        String id = UUID.randomUUID().toString();
        Transaction tx = new Transaction(id, accountId, type, amount, description, Instant.now());
        transactionsByAccount.computeIfAbsent(accountId, k -> new ArrayList<>()).add(tx);
        return tx;
    }

    public List<Transaction> list(String accountId) {
        return new ArrayList<>(transactionsByAccount.getOrDefault(accountId, List.of()));
    }
}
