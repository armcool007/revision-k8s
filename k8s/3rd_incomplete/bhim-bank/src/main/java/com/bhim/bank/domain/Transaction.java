package com.bhim.bank.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Transaction {
    private final String id; // UUID
    private final String accountId;
    private final TransactionType type;
    private final BigDecimal amount;
    private final String description;
    private final Instant occurredAt;

    public Transaction(String id, String accountId, TransactionType type, BigDecimal amount, String description, Instant occurredAt) {
        this.id = Objects.requireNonNull(id, "id");
        this.accountId = Objects.requireNonNull(accountId, "accountId");
        this.type = Objects.requireNonNull(type, "type");
        this.amount = Objects.requireNonNull(amount, "amount");
        this.description = description;
        this.occurredAt = occurredAt == null ? Instant.now() : occurredAt;
    }

    public String getId() { return id; }
    public String getAccountId() { return accountId; }
    public TransactionType getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public String getDescription() { return description; }
    public Instant getOccurredAt() { return occurredAt; }
}
