package com.ognjen.mini_e_commerce.dto;

import jakarta.validation.constraints.*;

public record AddToCartRequest(@NotNull Long productId,
                               @Positive int quantity) {
}
