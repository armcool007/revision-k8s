// File: src/main/java/com/bhim/bank/domain/Customer.java

package com.bhim.bank.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    private String id; // UUID as String

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @Column(nullable = false)
    private Instant createdAt;

    // JPA requires a no-arg constructor (can be protected)
    protected Customer() {}

    public Customer(String id, String name, String email, String phone, Instant createdAt) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt == null ? Instant.now() : createdAt;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Instant getCreatedAt() { return createdAt; }

    // Setters (required for JPA if using field access — optional but safe to include)
    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
