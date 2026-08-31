package com.uni.innovationConnect.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uni.innovationConnect.auth.dto.AdminCreateUserRequest;
import com.uni.innovationConnect.auth.dto.AuthResponse;
import com.uni.innovationConnect.auth.dto.LoginRequest;
import com.uni.innovationConnect.auth.dto.RegisterRequest;
import com.uni.innovationConnect.auth.dto.UpdateProfileRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
 @CrossOrigin(origins = {"http://localhost:5173","http://localhost:5177"})
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/admin/create-lecturer")
    public ResponseEntity<AuthResponse> createLecturer(
            @Valid @RequestBody AdminCreateUserRequest request) {

        return ResponseEntity.ok(
                authService.createLecturer(request));

    }

    @PostMapping("/admin/create-admin")
    public ResponseEntity<AuthResponse> createAdmin(
            @Valid @RequestBody AdminCreateUserRequest request) {

        return ResponseEntity.ok(
                authService.createAdmin(request));

    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<AuthResponse> updateProfile(

            @PathVariable Long userId,

            @RequestBody UpdateProfileRequest request) {

        return ResponseEntity.ok(
                authService.updateProfile(userId, request));
    }
}

// REGISTER
// {
// "username": "mary_smith",
// "email": "mary@example.com",
// "password": "SecurePass456",
// "firstName": "Mary",
// "lastName": "Smith",
// "role": "APPLICANT"
// }
// {
// "username": "rahma_suleiman",
// "email": "rahma@example.com",
// "password": "SecurePass789",
// "firstName": "Rahma",
// "lastName": "Suleiman",
// "role": "APPLICANT"
// }
// {
// "username": "rahma_suleiman",
// "email": "suleimanr166@gmail.com",
// "firstName": "Rahma",
// "lastName": "Suleiman",
// "address": "Mtwapa",
// "role": "APPLICANT"
// }

// {
// "username": "amina_hassan",
// "email": "amina@example.com",
// "password": "SecurePass123",
// "firstName": "Amina",
// "lastName": "Hassan",
// "role": "APPLICANT"
// }
// {
// "username": "shuayb_moha",
// "email": "moha@example.com",
// "password": "SecurePass667",
// "firstName": "Shuayb",
// "lastName": "Moha",
// "role": "ADMIN"
// }
// LOGIN
// {
// "usernameOrEmail": "mary_smith",
// "password": "SecurePass456"
// }
// {
// "usernameOrEmail": "shuayb_moha",
// "password": "SecurePass667"
// }
// {
// "usernameOrEmail": "rahma_suleiman",
// "password": "SecurePass789"
// }
//
