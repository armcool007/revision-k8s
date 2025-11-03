package com.bhim.bank.exception;

import java.time.Instant;

public record ApiError(String message, String path, Instant timestamp) {
    public static ApiError of(String message, String path) {
        return new ApiError(message, path, Instant.now());
    }
}
