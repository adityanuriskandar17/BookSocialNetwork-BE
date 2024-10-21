package com.aditni.book.auth;

import com.aditni.book.role.RoleRepository;
import com.aditni.book.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private RoleRepository roleRepository;

    public void register(RegistrationRequest request) {
        var userRole = roleRepository.findByName("USER")
                // todo - better exception handling
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initialized"));
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password()
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
    }
}
