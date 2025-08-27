package com.ognjen.mini_e_commerce.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ognjen.mini_e_commerce.model.User;
import com.ognjen.mini_e_commerce.model.UserRoles;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    @Getter
    private Long id;
    private String email;
    @Getter
    private String fullName;
    @JsonIgnore
    private String password;
    @Getter
    private final Set<UserRoles> roles;
    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(Long id, String email, String fullName, String password, Set<UserRoles> roles,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.roles = (roles == null || roles.isEmpty()) ? Set.of() : Collections.unmodifiableSet(EnumSet.copyOf(roles));
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {

        Set<UserRoles> roles = (user.getRoles() == null || user.getRoles().isEmpty()) ? Set.of() : EnumSet.copyOf(user.getRoles());

        Set<GrantedAuthority> authorities = roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toUnmodifiableSet());


        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getPassword(),
                roles,
                authorities
        );
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
