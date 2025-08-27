package com.ognjen.mini_e_commerce.controller;

import com.ognjen.mini_e_commerce.model.Product;
import com.ognjen.mini_e_commerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@TestPropertySource(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
})
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void listProducts_ShouldReturnProducts() throws Exception {
        Product testProduct = Product.builder()
                .id(1L)
                .name("Laptop")
                .price(new BigDecimal("999.99"))
                .category("ELECTRONICS")
                .active(true)
                .build();

        Page<Product> productPage = new PageImpl<>(List.of(testProduct));

        when(productService.list(any(), any(), any(), any(), any(), any(PageRequest.class)))
                .thenReturn(productPage);

        mockMvc.perform(get("/api/products")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void getProduct_WhenProductExists_ShouldReturnProduct() throws Exception {
        Product testProduct = Product.builder()
                .id(1L)
                .name("Laptop")
                .price(new BigDecimal("999.99"))
                .build();

        when(productService.get(1L)).thenReturn(testProduct);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk());
    }
}
