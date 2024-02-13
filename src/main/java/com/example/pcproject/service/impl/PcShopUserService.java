package com.example.pcproject.service.impl;

import com.example.pcproject.repository.UserRepository;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PcShopUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public PcShopUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(PcShopUserService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }

    private static UserDetails map(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(PcShopUserService::map).toList())
                .build();
    }

    private static GrantedAuthority map(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getRoles().name());

    }
}
