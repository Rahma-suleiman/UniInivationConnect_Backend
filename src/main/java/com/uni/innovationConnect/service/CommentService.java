package com.uni.innovationConnect.service;

import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.CommentDTO;
import com.uni.innovationConnect.model.Comment;
import com.uni.innovationConnect.model.Idea;
import com.uni.innovationConnect.model.User;
import com.uni.innovationConnect.repository.CommentRepository;
import com.uni.innovationConnect.repository.IdeaRepository;
import com.uni.innovationConnect.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final IdeaRepository ideaRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // Get all comments
    public List<CommentDTO> getAllComments() {

        return commentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

    }

    // Get comment by id
    public CommentDTO getCommentById(Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Comment not found"));

        return mapToDTO(comment);

    }

    // Create comment
    public CommentDTO createComment(CommentDTO dto) {

        Comment comment = new Comment();

        comment.setMessage(dto.getMessage());

        Idea idea = ideaRepository.findById(dto.getIdea())
                .orElseThrow(() -> new IllegalStateException("Idea not found"));

        User user = userRepository.findById(dto.getUser())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        comment.setIdea(idea);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);

        return mapToDTO(savedComment);

    }

    // Update comment
    public CommentDTO editComment(Long id, CommentDTO dto) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Comment not found"));

        comment.setMessage(dto.getMessage());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDTO(updatedComment);

    }

    // Delete comment
    public void deleteComment(Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Comment not found"));

        commentRepository.delete(comment);

    }

    public List<CommentDTO> getCommentsByIdea(Long ideaId) {

        return commentRepository.findByIdeaId(ideaId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

    }

    // Entity -> DTO
    private CommentDTO mapToDTO(Comment comment) {

        CommentDTO dto = modelMapper.map(comment, CommentDTO.class);

        if (comment.getIdea() != null) {
            dto.setIdea(comment.getIdea().getId());
        }

        if (comment.getUser() != null) {
            dto.setUser(comment.getUser().getId());
        }

        return dto;

    }

}