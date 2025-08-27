package com.ognjen.mini_e_commerce.service;

import com.ognjen.mini_e_commerce.dto.AuthResponse;
import com.ognjen.mini_e_commerce.dto.LoginRequest;
import com.ognjen.mini_e_commerce.dto.RegisterRequest;
import com.ognjen.mini_e_commerce.model.User;
import com.ognjen.mini_e_commerce.model.UserRoles;
import com.ognjen.mini_e_commerce.repo.UserRepository;
import com.ognjen.mini_e_commerce.security.JwtUtils;
import com.ognjen.mini_e_commerce.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<UserRoles> roles = new ArrayList<>(userDetails.getRoles());


        return new AuthResponse(jwt, "Bearer",
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFullName(),
                roles);
    }

    public AuthResponse registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new RuntimeException("Email is already in use!");
        }

        User user = new User();
        user.setEmail(registerRequest.email());
        user.setFullName(registerRequest.fullName());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));

        Set<UserRoles> roles = new HashSet<>();
        roles.add(UserRoles.ROLE_USER); // default role
        user.setRoles(roles);

        userRepository.save(user);


        return authenticateUser(new LoginRequest(registerRequest.email(), registerRequest.password()));
    }

}
