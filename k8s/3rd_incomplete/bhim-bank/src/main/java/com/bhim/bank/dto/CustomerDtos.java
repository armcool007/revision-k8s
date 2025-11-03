package com.bhim.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerDtos {
    public record Create(@NotBlank String name, @Email String email, String phone) {}
    public record Update(String name, String email, String phone) {}
}
