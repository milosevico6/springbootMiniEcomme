package com.ognjen.mini_e_commerce.dto;

import java.math.BigDecimal;

public record CartItemResponse(Long id,
                               Long productId,
                               String productName,
                               BigDecimal unitPrice,
                               int quantity,
                               BigDecimal totalPrice) {
}
