package com.uni.innovationConnect.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uni.innovationConnect.auth.dto.AdminCreateUserRequest;
import com.uni.innovationConnect.auth.dto.AuthResponse;
import com.uni.innovationConnect.auth.dto.LoginRequest;
import com.uni.innovationConnect.auth.dto.RegisterRequest;
import com.uni.innovationConnect.model.Role;
import com.uni.innovationConnect.model.User;

import com.uni.innovationConnect.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new IllegalStateException(
                    "Email already exists");

        }

        User user = new User();

        user.setFirstName(
                request.getFirstName());

        user.setLastName(
                request.getLastName());

        user.setEmail(
                request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        // Default role
        user.setRole(Role.STUDENT);

        userRepository.save(user);

        return new AuthResponse(

                user.getId(),

                user.getFirstName(),

                user.getLastName(),

                user.getEmail(),

                user.getRole().name(),

                "Registration successful"

        );

    }

    public AuthResponse createLecturer(
            AdminCreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new IllegalStateException(
                    "Email already exists");

        }

        User user = new User();

        user.setFirstName(
                request.getFirstName());

        user.setLastName(
                request.getLastName());

        user.setEmail(
                request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        // Backend assigns lecturer role
        user.setRole(Role.LECTURER);

        userRepository.save(user);

        return new AuthResponse(

                user.getId(),

                user.getFirstName(),

                user.getLastName(),

                user.getEmail(),

                user.getRole().name(),

                "Lecturer created successfully"

        );

    }

    public AuthResponse createAdmin(
            AdminCreateUserRequest request) {

        User user = new User();

        user.setFirstName(
                request.getFirstName());

        user.setLastName(
                request.getLastName());

        user.setEmail(
                request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        // Backend assigns admin role
        user.setRole(Role.ADMIN);

        userRepository.save(user);

        return new AuthResponse(

                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().name(),
                "Admin created successfully"

        );

    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())

                .orElseThrow(() -> new IllegalStateException(
                        "Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new IllegalStateException(
                    "Invalid email or password");

        }

        return new AuthResponse(

                user.getId(),

                user.getFirstName(),

                user.getLastName(),

                user.getEmail(),

                user.getRole().name(),

                "Login successful"

        );

    }
}
// register lecturer
// {
//   "firstName": "Asha",
//   "lastName": "Ali",
//   "email": "asha@suza.ac.tz",
//   "password": "asha@123"
// }
// {
//   "firstName": "Hassan",
//   "lastName": "Said",
//   "email": "hassan@suza.ac.tz",
//   "password": "hassan@123"
// }
// {
//   "firstName": "Fatma",
//   "lastName": "Salim",
//   "email": "fatma@suza.ac.tz",
//   "password": "fatma@123"
// }

// register student
// {
//   "firstName": "Rahma",
//   "lastName": "Suleiman",
//   "email": "rahma@stu.suza.ac.tz",
//   "password": "rahma@123"
// }
// {
//   "firstName": "Shuayb",
//   "lastName": "Mohamed",
//   "email": "shuayb@stu.suza.ac.tz",
//   "password": "shuayb@123"
// }
// {
//   "firstName": "Zainab",
//   "lastName": "Hassan",
//   "email": "zainab@stu.suza.ac.tz",
//   "password": "zainab@123"
// }