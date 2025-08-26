package com.ognjen.mini_e_commerce.service;

import com.ognjen.mini_e_commerce.model.CartItem;
import com.ognjen.mini_e_commerce.repo.CartItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    private final CartItemRepository items;

    public CartItemService(CartItemRepository items) {
        this.items = items;
    }

    public CartItem getCartItemForUser(Long itemId, Long userId) {
        return items.findByIdAndCart_User_Id(itemId, userId).orElseThrow(() -> new EntityNotFoundException("Cart item not found"));
    }

    public void deleteForUser(Long itemId, Long userId) {
        CartItem ci = getCartItemForUser(itemId, userId);
        items.delete(ci);
    }
}
