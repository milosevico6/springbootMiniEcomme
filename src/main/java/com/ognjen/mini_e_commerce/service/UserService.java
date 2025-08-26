package com.ognjen.mini_e_commerce.service;

import com.ognjen.mini_e_commerce.model.Cart;
import com.ognjen.mini_e_commerce.model.User;
import com.ognjen.mini_e_commerce.repo.CartRepository;
import com.ognjen.mini_e_commerce.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service@Transactional@Getter
public class UserService {

    private final UserRepository users;
    private final CartRepository carts;

    public UserService(UserRepository users, CartRepository carts) {
        this.users = users;
        this.carts = carts;
    }

    public User get(Long id) {
        return users.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

    }

    public Optional<User> findByEmail(String email) {
        return users.findByEmail(email);
    }


    public Cart ensureCart(User user) {
        return carts.findByUserid(user.getId()).orElseGet(() ->
                {
                    Cart c = Cart.builder().user(user).build();
                    return carts.save(c);
                });
    }


}
