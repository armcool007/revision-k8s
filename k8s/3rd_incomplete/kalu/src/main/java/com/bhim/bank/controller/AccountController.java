package com.bhim.bank.controller;

import com.bhim.bank.domain.Account;
import com.bhim.bank.domain.Transaction;
import com.bhim.bank.domain.TransactionType;
import com.bhim.bank.dto.AccountDtos;
import com.bhim.bank.service.AccountService;
import com.bhim.bank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@Valid @RequestBody AccountDtos.Create req) {
        return accountService.create(req.customerId(), req.type(), req.currency(), req.openingBalance());
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable String id) {
        return accountService.get(id).orElseThrow(() -> new java.util.NoSuchElementException("Account not found"));
    }

    @GetMapping("/by-customer/{customerId}")
    public List<Account> listByCustomer(@PathVariable String customerId) {
        return accountService.listByCustomer(customerId);
    }

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deposit(@Valid @RequestBody AccountDtos.Cash req) {
        accountService.deposit(req.accountId(), req.amount());
        transactionService.record(req.accountId(), TransactionType.DEPOSIT, req.amount(), "Cash deposit");
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void withdraw(@Valid @RequestBody AccountDtos.Cash req) {
        accountService.withdraw(req.accountId(), req.amount());
        transactionService.record(req.accountId(), TransactionType.WITHDRAWAL, req.amount(), "Cash withdrawal");
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void transfer(@Valid @RequestBody AccountDtos.Transfer req) {
        accountService.transfer(req.fromAccountId(), req.toAccountId(), req.amount());
        transactionService.record(req.fromAccountId(), TransactionType.TRANSFER_OUT, req.amount(), "Transfer to " + req.toAccountId());
        transactionService.record(req.toAccountId(), TransactionType.TRANSFER_IN, req.amount(), "Transfer from " + req.fromAccountId());
    }

    @GetMapping("/{accountId}/transactions")
    public List<Transaction> listTransactions(@PathVariable String accountId) {
        return transactionService.list(accountId);
    }
}
