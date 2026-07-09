package com.uni.innovationConnect.auth;


import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uni.innovationConnect.auth.dto.LoginRequest;
import com.uni.innovationConnect.auth.dto.RegisterRequest;
import com.uni.innovationConnect.model.User;
import com.uni.innovationConnect.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    // Register
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        // Encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        userRepository.save(user);

        return "User registered successfully.";
    }

    // Login
    public String login(LoginRequest request) {

        Optional<User> userOpt = userRepository.findByEmail(request.getUsernameOrEmail());

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOpt.get();

        // Compare encrypted password
        if (!passwordEncoder.matches(request.getPassword(),
                user.getPassword())) {

            throw new IllegalArgumentException("Invalid password.");
        }

        return "Login successful.";
    }

    // Logout
    public String logout() {

        return "Logout successful.";
    }

}