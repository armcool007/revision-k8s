package com.bhim.bank.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Account {
    private final String id;
    private final String customerId;
    private final String accountNumber;
    private final AccountType type;
    private final String currency;
    private BigDecimal balance;
    private AccountStatus status;
    private final Instant createdAt;

    public Account(
            String id,
            String customerId,
            String accountNumber,
            AccountType type,
            String currency,
            BigDecimal openingBalance,
            Instant createdAt
    ) {
        this.id = Objects.requireNonNull(id, "id");
        this.customerId = Objects.requireNonNull(customerId, "customerId");
        this.accountNumber = Objects.requireNonNull(accountNumber, "accountNumber");
        this.type = Objects.requireNonNull(type, "type");
        this.currency = Objects.requireNonNull(currency, "currency");
        this.balance = openingBalance == null ? BigDecimal.ZERO : openingBalance;
        this.status = AccountStatus.ACTIVE;
        this.createdAt = createdAt == null ? Instant.now() : createdAt;
    }

    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public String getAccountNumber() { return accountNumber; }
    public AccountType getType() { return type; }
    public String getCurrency() { return currency; }
    public BigDecimal getBalance() { return balance; }
    public AccountStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }

    public void setStatus(AccountStatus status) { this.status = Objects.requireNonNull(status); }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) throw new IllegalArgumentException("Amount must be positive");
        ensureActive();
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) throw new IllegalArgumentException("Amount must be positive");
        ensureActive();
        if (balance.compareTo(amount) < 0) throw new IllegalStateException("Insufficient funds");
        balance = balance.subtract(amount);
    }

    private void ensureActive() {
        if (status != AccountStatus.ACTIVE) throw new IllegalStateException("Account is not active");
    }
}
