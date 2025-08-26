package com.ognjen.mini_e_commerce.repo;

import com.ognjen.mini_e_commerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {


    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
