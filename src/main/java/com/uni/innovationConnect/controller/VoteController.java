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

        @PostMapping
        public ResponseEntity<VoteDTO> createVote(
                        @RequestBody VoteDTO dto) {

                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(
                                                voteService.createVote(dto));

        }

        @GetMapping("/idea/{ideaId}")
        public ResponseEntity<List<VoteDTO>> getVotesByIdea(
                        @PathVariable Long ideaId) {

                return ResponseEntity.ok(
                                voteService.getVotesByIdea(ideaId));

        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteVote(
                        @PathVariable Long id) {

                voteService.deleteVote(id);

                return ResponseEntity
                                .noContent()
                                .build();

        }

}