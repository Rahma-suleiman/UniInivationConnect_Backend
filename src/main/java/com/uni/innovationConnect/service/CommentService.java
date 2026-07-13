package com.uni.innovationConnect.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.CommentDTO;
import com.uni.innovationConnect.enums.IdeaStatus;
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

        // Check if the comment message is empty or not provided
        if (dto.getMessage() == null
                || dto.getMessage().isBlank()) {

            throw new IllegalStateException(
                    "Comment message is required");
        }

        // Check if the comment has a related idea and user
        // A comment must belong to an idea and must be created by a user
        if (dto.getIdeaId() == null || dto.getUserId() == null) {
            throw new IllegalStateException("Idea and user are required");
        }

        // Find the idea that the user wants to comment on
        // The idea must exist in the database
        Idea idea = ideaRepository.findById(dto.getIdeaId())
                .orElseThrow(() -> new IllegalStateException("Idea not found"));

        // Find the user who is creating the comment
        // The user must exist in the database
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalStateException(
                        "User not found"));

        // Prevent users from commenting on rejected ideas
        // Rejected ideas are already closed in the idea lifecycle
        if (idea.getStatus() == IdeaStatus.REJECTED) {
            throw new IllegalStateException("Cannot comment on rejected idea");
        }

        // Create a new Comment object
        Comment comment = new Comment();

        // Store the comment message entered by the user
        comment.setMessage(dto.getMessage());

        // Connect the comment with the idea
        comment.setIdea(idea);

        // Connect the comment with the user who created it
        comment.setUser(user);

        // Save the comment into the database
        // Convert the saved entity into CommentDTO before returning response
        return mapToDTO(
                commentRepository.save(comment));

    }

    // Update comment
    public CommentDTO editComment(Long id, CommentDTO dto) {

        Comment comment = commentRepository.findById(id)

                .orElseThrow(() -> new IllegalStateException(
                        "Comment not found"));

        comment.setMessage(dto.getMessage());

        Comment updated = commentRepository.save(comment);

        return mapToDTO(updated);

    }

    // Delete comment
    public void deleteComment(Long id) {

        Comment comment = commentRepository.findById(id)

                .orElseThrow(() -> new IllegalStateException(
                        "Comment not found"));

        commentRepository.delete(comment);

    }

    // Get comments by idea
    public List<CommentDTO> getCommentsByIdea(Long ideaId) {

        return commentRepository.findByIdeaId(ideaId)

                .stream()

                .map(this::mapToDTO)

                .collect(Collectors.toList());

    }

    // Entity -> DTO
    private CommentDTO mapToDTO(Comment comment) {

        CommentDTO dto = new CommentDTO();

        dto.setId(comment.getId());

        dto.setMessage(comment.getMessage());

        if (comment.getIdea() != null) {

            dto.setIdeaId(
                    comment.getIdea().getId());

        }

        if (comment.getUser() != null) {

            dto.setUserId(
                    comment.getUser().getId());

        }

        return dto;

    }

}
// Student 6 comments on Student 5's idea (Idea 1)

// {
// "message": "This is a great idea. It can help farmers increase crop
// production and reduce water waste.",
// "ideaId": 1,
// "userId": 6
// }
// Student 7 comments on Student 6's idea (Idea 2)
// {
// "message": "Very useful for students. It will make it easier to recover lost
// items on campus.",
// "ideaId": 2,
// "userId": 7
// }
// Student 5 comments on Student 7's idea (Idea 3)
// {
// "message": "This system will improve access to healthcare services and reduce
// waiting time for students.",
// "ideaId": 3,
// "userId": 5
// }