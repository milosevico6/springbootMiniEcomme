package com.ognjen.mini_e_commerce.service;

import com.ognjen.mini_e_commerce.model.Product;
import com.ognjen.mini_e_commerce.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void get_WhenProductExists_ShouldReturnProduct() {
        Product testProduct = Product.builder()
                .id(1L)
                .name("Laptop")
                .price(new BigDecimal("999.99"))
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        Product result = productService.get(1L);

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
    }

    @Test
    void get_WhenProductNotFound_ShouldThrowException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            productService.get(1L);
        });
    }
}
