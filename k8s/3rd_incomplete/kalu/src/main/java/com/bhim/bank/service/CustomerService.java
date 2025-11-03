package com.bhim.bank.service;

import com.bhim.bank.domain.Customer;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final Map<String, Customer> customersById = new ConcurrentHashMap<>();

    public Customer create(String name, String email, String phone) {
        String id = UUID.randomUUID().toString();
        Customer customer = new Customer(id, name, email, phone, Instant.now());
        customersById.put(id, customer);
        return customer;
    }

    public Optional<Customer> get(String id) {
        return Optional.ofNullable(customersById.get(id));
    }

    public List<Customer> list() {
        return new ArrayList<>(customersById.values());
    }

    public Optional<Customer> update(String id, String name, String email, String phone) {
        Customer existing = customersById.get(id);
        if (existing == null) return Optional.empty();
        if (name != null && !name.isBlank()) existing.setName(name);
        if (email != null && !email.isBlank()) existing.setEmail(email);
        if (phone != null && !phone.isBlank()) existing.setPhone(phone);
        return Optional.of(existing);
    }

    public boolean delete(String id) {
        return customersById.remove(id) != null;
    }
}
