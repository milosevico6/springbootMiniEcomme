package com.ognjen.mini_e_commerce.security;
import com.ognjen.mini_e_commerce.repo.UserRepository;
import com.ognjen.mini_e_commerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u1 = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with that email: " +email + " not found!"));
        return UserDetailsImpl.build(u1);
    }
}
