package com.ognjen.mini_e_commerce.service;

import com.ognjen.mini_e_commerce.model.User;
import com.ognjen.mini_e_commerce.repo.UserRepository;
import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
