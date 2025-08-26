package com.ognjen.mini_e_commerce.repo;

import com.ognjen.mini_e_commerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByIdAndCart_User_Id(Long id, Long userId);

    Optional<CartItem> findByCart_IdAndProduct_Id(Long cartId, Long productId);

    boolean existsByIdAndCart_User_Id(Long id, Long userId);

    boolean existsByCart_IdAndProduct_Id(Long cartId, Long productId);
}
