package com.bhim.bank.controller;

import com.bhim.bank.domain.Customer;
import com.bhim.bank.dto.CustomerDtos;
import com.bhim.bank.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@Valid @RequestBody CustomerDtos.Create req) {
        return customerService.create(req.name(), req.email(), req.phone());
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable String id) {
        return customerService.get(id).orElseThrow(() -> new java.util.NoSuchElementException("Customer not found"));
    }

    @GetMapping
    public List<Customer> list() {
        return customerService.list();
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable String id, @Valid @RequestBody CustomerDtos.Update req) {
        return customerService.update(id, req.name(), req.email(), req.phone()).orElseThrow(() -> new java.util.NoSuchElementException("Customer not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        if (!customerService.delete(id)) {
            throw new java.util.NoSuchElementException("Customer not found");
        }
    }
}
