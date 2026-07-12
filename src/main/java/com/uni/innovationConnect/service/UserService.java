package com.uni.innovationConnect.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.IdeaDTO;
import com.uni.innovationConnect.dto.UserDTO;
import com.uni.innovationConnect.model.User;
import com.uni.innovationConnect.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    // Convert Entity to DTO
    private UserDTO mapToDTO(User user) {

        return modelMapper.map(
                user,
                UserDTO.class);

    }

    // Get all users
    public List<UserDTO> getAllUsers() {

        return userRepository
                .findAll()
                .stream()

                .map(this::mapToDTO)

                .toList();

    }

    // Get user by id
    public UserDTO getUserById(Long id) {

        User user = userRepository
                .findById(id)

                .orElseThrow(() -> new IllegalStateException(
                        "User not found"));

        return mapToDTO(user);

    }

    // Get user ideas
    public List<IdeaDTO> getUserIdeas(Long id) {

        User user = userRepository
                .findById(id)

                .orElseThrow(() -> new IllegalStateException(
                        "User not found"));

        return user.getIdeas()

                .stream()

                .map(idea -> modelMapper.map(
                        idea,
                        IdeaDTO.class))

                .toList();

    }

    // Delete user
    public void deleteUser(Long id) {

        User user = userRepository
                .findById(id)

                .orElseThrow(() -> new IllegalStateException(
                        "User not found"));

        userRepository.delete(user);

    }

}