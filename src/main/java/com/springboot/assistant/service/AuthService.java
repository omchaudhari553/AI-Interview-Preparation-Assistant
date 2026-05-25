package com.springboot.assistant.service;

import com.springboot.assistant.dto.LoginRequest;
import com.springboot.assistant.dto.RegisterRequest;
import com.springboot.assistant.model.Role;
import com.springboot.assistant.model.User;
import com.springboot.assistant.repository.UserRepository;
import com.springboot.assistant.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public void register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(
                     passwordEncoder.encode(
                     request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    public String login(LoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(
                user.getUsername());
    }
}
