package com.ognjen.mini_e_commerce.repo;

import com.ognjen.mini_e_commerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserid(Long userId);
    boolean existByUserId(Long userId);
}
