package com.ognjen.mini_e_commerce.service;

import com.ognjen.mini_e_commerce.dto.CartResponse;
import com.ognjen.mini_e_commerce.model.Cart;
import com.ognjen.mini_e_commerce.model.User;
import jakarta.persistence.EntityNotFoundException;
import com.ognjen.mini_e_commerce.repo.CartItemRepository;
import com.ognjen.mini_e_commerce.repo.CartRepository;
import com.ognjen.mini_e_commerce.repo.ProductRepository;
import com.ognjen.mini_e_commerce.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    void getCart_ShouldReturnCartResponse() {
        User testUser = User.builder().id(1L).email("test@example.com").build();
        Cart testCart = Cart.builder().id(1L).user(testUser).items(new ArrayList<>()).build();
        
        when(cartRepository.findByUser_Id(1L)).thenReturn(Optional.of(testCart));

        CartResponse result = cartService.getCart(1L);

        assertNotNull(result);
        assertEquals(0, result.totalItems());
    }

    @Test
    void getCart_WhenUserNotFound_ShouldThrowException() {
        when(cartRepository.findByUser_Id(1L)).thenReturn(Optional.empty());

        assertThrows(jakarta.persistence.EntityNotFoundException.class, () -> {
            cartService.getCart(1L);
        });
    }
}
