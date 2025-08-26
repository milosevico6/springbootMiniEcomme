package com.ognjen.mini_e_commerce.service;

import com.ognjen.mini_e_commerce.dto.AddToCartRequest;
import com.ognjen.mini_e_commerce.dto.CartItemResponse;
import com.ognjen.mini_e_commerce.dto.CartResponse;
import com.ognjen.mini_e_commerce.dto.UpdateCartItemRequest;
import com.ognjen.mini_e_commerce.model.Cart;
import com.ognjen.mini_e_commerce.model.CartItem;
import com.ognjen.mini_e_commerce.model.Product;
import com.ognjen.mini_e_commerce.model.User;
import com.ognjen.mini_e_commerce.repo.CartItemRepository;
import com.ognjen.mini_e_commerce.repo.CartRepository;
import com.ognjen.mini_e_commerce.repo.ProductRepository;
import com.ognjen.mini_e_commerce.repo.UserRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository carts;
    private final CartItemRepository items;
    private final ProductRepository products;
    private final UserRepository users;

    public CartService(CartRepository carts, CartItemRepository items,ProductRepository products, UserRepository users) {
        this.carts = carts; this.items = items; this.products = products; this.users = users;
    }

    private User getUser(Long userId) {
        return users.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

    }

    private Cart ensureCart(Long userId) {
        return carts.findByUser_Id(userId).orElseGet(() -> carts.save(Cart.builder().user(getUser(userId)).build()));

    }
    public CartItem addItem(Long userId, @Valid AddToCartRequest req) {
        Product p = products.findById(req.productId()).orElseThrow(() -> new EntityNotFoundException("Product  not found"));
        if (!p.isActive()) throw new IllegalArgumentException("Product is not active");

        Cart cart = ensureCart(userId);
        Optional<CartItem> existing = cart.getItems().stream().filter(ci -> ci.getProduct().getId().equals(p.getId())).findFirst();

        if (existing.isPresent()) {
            CartItem ci = existing.get();
            ci.setQuantity(ci.getQuantity() + req.quantity());
            return items.save(ci);
        }

        CartItem ci = CartItem.builder()
                .cart(cart)
                .product(p)
                .quantity(req.quantity())
                .unitPrice(p.getPrice())
                .build();
        cart.getItems().add(ci);
        return items.save(ci);
        }





    @Transactional(readOnly = true)
    public CartResponse getCart(Long userId) {
        Cart cart = ensureCart(userId);
        List<CartItemResponse> itemDtos = new ArrayList<>();
        for (CartItem ci : cart.getItems()) {
            CartItemResponse dto = new CartItemResponse(
                    ci.getId(),
                    ci.getProduct().getId(),
                    ci.getProduct().getName(),
                    ci.getUnitPrice(),
                    ci.getQuantity(),
                    ci.getTotalPrice()
            );
            itemDtos.add(dto);
        }

        int totalItems = 0;
        for (CartItemResponse item : itemDtos) {
            totalItems += item.quantity();
        }


        BigDecimal priceInCart = BigDecimal.ZERO;
        for (CartItemResponse item : itemDtos) {
            priceInCart = priceInCart.add(item.totalPrice());
        }

        return new CartResponse(itemDtos, totalItems, priceInCart);


    }


    public CartItem updateItem(Long userId, Long itemId, UpdateCartItemRequest req) {
        CartItem item = items.findByIdAndCart_User_Id(itemId, userId).orElseThrow(() -> new EntityNotFoundException("Cart item not found"));
        item.setQuantity(req.quantity());
        return items.save(item);
    }


    public void removeItem(Long userId, Long itemId) {
        CartItem item = items.findByIdAndCart_User_Id(itemId, userId).orElseThrow(() -> new EntityNotFoundException("Cart item not found"));
        items.delete(item);
    }
}

