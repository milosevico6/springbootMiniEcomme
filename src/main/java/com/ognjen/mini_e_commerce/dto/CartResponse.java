package com.ognjen.mini_e_commerce.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(List<CartItemResponse> items,
                           int totalItems,
                           BigDecimal grandTotal) {
}
