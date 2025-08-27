package com.ognjen.mini_e_commerce.controller;

import com.ognjen.mini_e_commerce.dto.AddToCartRequest;
import com.ognjen.mini_e_commerce.dto.CartResponse;
import com.ognjen.mini_e_commerce.dto.UpdateCartItemRequest;
import com.ognjen.mini_e_commerce.model.CartItem;
import com.ognjen.mini_e_commerce.service.CartService;


import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {


    private final CartService cartService;

    public CartController(CartService service) {
        this.cartService = service;
    }
    private Long uid() { return 1L; }

    @PostMapping("/add")
    public ResponseEntity<CartItem> add(@Valid @RequestBody AddToCartRequest req) {
        CartItem newCartItem = cartService.addItem(uid(), req);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCartItem);
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart() {
        return ResponseEntity.ok(cartService.getCart(uid()));
    }

    @PutMapping("/item/update/{id}")
    public ResponseEntity<CartItem> update(@PathVariable Long id,@Valid @RequestBody UpdateCartItemRequest req) {
        return ResponseEntity.ok(cartService.updateItem(uid(), id, req));
    }

    @DeleteMapping("/item/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        cartService.removeItem(uid(), id);
        return ResponseEntity.ok("Proizvod je uspešno obrisan.");
    }


}
