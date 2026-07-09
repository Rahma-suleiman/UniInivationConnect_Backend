package com.uni.innovationConnect.controller;

import com.uni.innovationConnect.dto.CommentDTO;
import com.uni.innovationConnect.service.CommentService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/innovationConnect/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        CommentDTO comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    // Since comments are always displayed under an idea,
    // it's useful to add an endpoint to retrieve comments for
    // a specific idea.
    @GetMapping("/idea/{ideaId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByIdea(@PathVariable Long ideaId) {
        return ResponseEntity.ok(commentService.getCommentsByIdea(ideaId));
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO dto) {
        CommentDTO comment = commentService.createComment(dto);
        // return new ResponseEntity<>(comment, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> editComment(@PathVariable Long id, @RequestBody CommentDTO dto) {
        CommentDTO comment = commentService.editComment(id, dto);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
