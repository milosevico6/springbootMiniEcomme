package com.ognjen.mini_e_commerce.repo;

import com.ognjen.mini_e_commerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByIdAndCart_User_Id(Long id, Long userId);
    // Optional<CartItem> findByUser_IdAndProduct_Id(Long userId, Long productId);
    Optional<CartItem> findByCart_IdAndProduct_Id(Long cartId, Long productId);
    Optional<CartItem> findByCart_User_IdAndProduct_Id(Long userId, Long productId);
    List<CartItem> findAllByCart_User_Id(Long userId);
    boolean existsByIdAndCart_User_Id(Long id, Long userId);

    boolean existsByCart_IdAndProduct_Id(Long cartId, Long productId);
}
