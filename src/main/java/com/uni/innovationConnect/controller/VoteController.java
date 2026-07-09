package com.uni.innovationConnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uni.innovationConnect.dto.VoteDTO;
import com.uni.innovationConnect.service.VoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/innovationConnect/vote")
@RequiredArgsConstructor
public class VoteController {
        private final VoteService voteService;

        @GetMapping
        public ResponseEntity<List<VoteDTO>> getAllVotes() {
                List<VoteDTO> votes = voteService.getAllVotes();
                return new ResponseEntity<>(votes, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<VoteDTO> getVoteById(@PathVariable Long id) {
                VoteDTO vote = voteService.getVoteById(id);
                return ResponseEntity.ok(vote);
        }

// Get all votes for a specific idea
        @GetMapping("/idea/{ideaId}")
        public ResponseEntity<List<VoteDTO>> getVotesByIdea(
                        @PathVariable Long ideaId) {

                List<VoteDTO> votes = voteService.getVotesByIdea(ideaId);

                return ResponseEntity.ok(votes);

        }

        @PostMapping
        public ResponseEntity<VoteDTO> createVote(@RequestBody VoteDTO dto) {
                VoteDTO vote = voteService.createVote(dto);
                // return new ResponseEntity<>(vote, HttpStatus.CREATED);
                return ResponseEntity.status(HttpStatus.CREATED).body(vote);

        }

        @PutMapping("/{id}")
        public ResponseEntity<VoteDTO> editVote(@PathVariable Long id, @RequestBody VoteDTO dto) {
                VoteDTO vote = voteService.editVote(id, dto);
                return ResponseEntity.ok(vote);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteVote(@PathVariable Long id) {
                voteService.deleteVote(id);
                return ResponseEntity.noContent().build();
        }

}
