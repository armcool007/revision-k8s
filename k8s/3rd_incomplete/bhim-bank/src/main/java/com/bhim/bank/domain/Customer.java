package com.bhim.bank.domain;

import java.time.Instant;
import java.util.Objects;

public class Customer {
    private final String id; // UUID
    private String name;
    private String email;
    private String phone;
    private final Instant createdAt;

    public Customer(String id, String name, String email, String phone, Instant createdAt) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt == null ? Instant.now() : createdAt;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Instant getCreatedAt() { return createdAt; }

    public void setName(String name) { this.name = Objects.requireNonNull(name); }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
}
