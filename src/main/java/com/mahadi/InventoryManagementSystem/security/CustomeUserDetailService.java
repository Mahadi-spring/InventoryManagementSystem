package com.mahadi.InventoryManagementSystem.security;

import com.mahadi.InventoryManagementSystem.exceptions.NotFoundException;
import com.mahadi.InventoryManagementSystem.entity.User;
import com.mahadi.InventoryManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomeUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInfo = userRepository.findByEmail(username)
                .orElseThrow(()-> new NotFoundException("User email not found"));

        return AuthUser.builder().user(userInfo).build();
    }
}
