package com.bhim.bank.dto;

import com.bhim.bank.domain.AccountType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class AccountDtos {
    public record Create(
            @NotBlank String customerId,
            @NotNull AccountType type,
            @NotBlank String currency,
            @NotNull @Min(0) BigDecimal openingBalance
    ) {}

    public record Transfer(@NotBlank String fromAccountId, @NotBlank String toAccountId, @NotNull @Min(1) BigDecimal amount) {}

    public record Cash(@NotBlank String accountId, @NotNull @Min(1) BigDecimal amount) {}
}
