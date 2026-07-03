package com.uni.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uni.auth.dto.LoginRequest;
import com.uni.auth.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }

    // Logout
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        return ResponseEntity.ok(authService.logout());
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

