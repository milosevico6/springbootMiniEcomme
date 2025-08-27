package com.ognjen.mini_e_commerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ognjen.mini_e_commerce.dto.CartResponse;
import com.ognjen.mini_e_commerce.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@TestPropertySource(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
})
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCart_ShouldReturnCart() throws Exception {
        CartResponse testCartResponse = new CartResponse(
                new ArrayList<>(),
                0,
                BigDecimal.ZERO
        );

        when(cartService.getCart(anyLong())).thenReturn(testCartResponse);

        mockMvc.perform(get("/api/cart")
                        .requestAttr("uid", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCart_WhenUserNotFound_ShouldReturnError() throws Exception {
        when(cartService.getCart(anyLong())).thenThrow(new jakarta.persistence.EntityNotFoundException("User not found"));

        mockMvc.perform(get("/api/cart")
                        .requestAttr("uid", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
