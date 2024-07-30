package org.example.javaspringhwgeekbrains.seminar5.security;

import lombok.RequiredArgsConstructor;
import org.example.javaspringhwgeekbrains.seminar5.model.Users;
import org.example.javaspringhwgeekbrains.seminar5.repository.RoleRepository;
import org.example.javaspringhwgeekbrains.seminar5.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyCustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        List<SimpleGrantedAuthority> userRoles = user.getRoles().stream()
                .map(it -> new SimpleGrantedAuthority(it.getName()))
                .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                userRoles
        );
    }
}
