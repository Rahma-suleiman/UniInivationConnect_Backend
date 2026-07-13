package com.uni.innovationConnect.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uni.innovationConnect.dto.UserDTO;
import com.uni.innovationConnect.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v2/innovationConnect/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {


    private final UserService userService;



    // Get all users (Admin)
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){

        return ResponseEntity.ok(
                userService.getAllUsers()
        );

    }



    // Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                userService.getUserById(id)
        );

    }



    // Get student ideas
    @GetMapping("/{id}/ideas")
    public ResponseEntity<?> getUserIdeas(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                userService.getUserIdeas(id)
        );

    }



    // Delete user (Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id
    ){

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();

    }


}