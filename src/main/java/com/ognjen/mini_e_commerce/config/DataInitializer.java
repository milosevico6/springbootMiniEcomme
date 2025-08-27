package com.ognjen.mini_e_commerce.config;

import com.ognjen.mini_e_commerce.model.Product;
import com.ognjen.mini_e_commerce.model.User;
import com.ognjen.mini_e_commerce.model.UserRoles;
import com.ognjen.mini_e_commerce.repo.ProductRepository;
import com.ognjen.mini_e_commerce.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        seedUsers();
        seedProducts();
    }

    private void seedUsers() {
        if (userRepository.count() > 0) return;

        User admin = new User();
        admin.setEmail("admin@example.com");
        admin.setFullName("Admin User");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRoles(EnumSet.of(UserRoles.ROLE_ADMIN, UserRoles.ROLE_USER));

        User user = new User();
        user.setEmail("user@example.com");
        user.setFullName("Regular User");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRoles(EnumSet.of(UserRoles.ROLE_USER));

        userRepository.saveAll(List.of(admin, user));

    }
    private void seedProducts() {
        if (productRepository.count() > 0) return;

        Product p1 = new Product();
        p1.setName("Wireless Mouse");
        p1.setDescription("2.4GHz bežični miš sa tihim klikovima");
        p1.setCategory("Peripherals");
        p1.setPrice(new BigDecimal("19.99"));
        p1.setActive(true);

        Product p2 = new Product();
        p2.setName("Mechanical Keyboard");
        p2.setDescription("Blue switch, RGB, full-size");
        p2.setCategory("Peripherals");
        p2.setPrice(new BigDecimal("59.99"));
        p2.setActive(true);

        productRepository.saveAll(List.of(p1, p2));

    }


}
