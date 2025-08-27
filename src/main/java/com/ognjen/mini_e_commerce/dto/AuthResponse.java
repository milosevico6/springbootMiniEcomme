package com.ognjen.mini_e_commerce.dto;

import com.ognjen.mini_e_commerce.model.UserRoles;

import java.util.List;

public record AuthResponse(String token,
                           String type,
                           Long id,
                           String email,
                           String fullName,
                           List<UserRoles> roles) {
    public AuthResponse(String token, Long id, String email, String fullName, List<UserRoles> roles) {
        this(token, "Bearer", id, email, fullName, roles);
    }
}
