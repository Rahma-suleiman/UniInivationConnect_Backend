package com.uni.innovationConnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uni.innovationConnect.dto.VoteDTO;
import com.uni.innovationConnect.dto.VoteResponseDTO;
import com.uni.innovationConnect.service.VoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/innovationConnect/vote")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class VoteController {

    private final VoteService voteService;

    // Get all votes
    @GetMapping
    public ResponseEntity<List<VoteDTO>> getAllVotes() {

        List<VoteDTO> votes = voteService.getAllVotes();

        return ResponseEntity.ok(votes);

    }

    // Get vote by id
    @GetMapping("/{id}")
    public ResponseEntity<VoteDTO> getVoteById(
            @PathVariable Long id) {

        VoteDTO vote = voteService.getVoteById(id);

        return ResponseEntity.ok(vote);

    }

    // Create vote
    @PostMapping
    public ResponseEntity<VoteDTO> createVote(
            @RequestBody VoteDTO dto) {

        VoteDTO vote = voteService.createVote(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vote);

    }

    // Get votes for one idea
    @GetMapping("/idea/{ideaId}")
    public ResponseEntity<List<VoteResponseDTO>> getVotesByIdea(
            @PathVariable Long ideaId) {

        List<VoteResponseDTO> votes = voteService.getVotesByIdea(ideaId);

        return ResponseEntity.ok(votes);

    }

    // Delete vote
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(
            @PathVariable Long id) {

        voteService.deleteVote(id);

        return ResponseEntity.noContent().build();

    }

}