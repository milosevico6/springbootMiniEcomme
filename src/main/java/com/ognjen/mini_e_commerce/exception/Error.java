package com.ognjen.mini_e_commerce.exception;

import java.time.Instant;

public record Error(Instant timestamp, int status, String error, String message, String path) {
}
