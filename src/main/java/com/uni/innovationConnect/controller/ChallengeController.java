package com.uni.innovationConnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uni.innovationConnect.dto.ChallengeDTO;
import com.uni.innovationConnect.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/innovationConnect/challenge")
@RequiredArgsConstructor
public class ChallengeController {

    
    private final ChallengeService challengeService;

    @GetMapping
    public ResponseEntity<List<ChallengeDTO>> getAllChallenges() {
        List<ChallengeDTO> challenge = challengeService.getAllChallenges();
        return new ResponseEntity<>(challenge, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeDTO> getChallengeById(@PathVariable Long id) {
        ChallengeDTO attend = challengeService.getChallengeById(id);
        return ResponseEntity.ok(attend);
    }

    @PostMapping
    public ResponseEntity<ChallengeDTO> createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        ChallengeDTO challenge = challengeService.createChallenge(challengeDTO);
        return new ResponseEntity<>(challenge, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<ChallengeDTO> editChallenge(@PathVariable Long id, @RequestBody ChallengeDTO challengeDTO) {
        ChallengeDTO attend = challengeService.editChallenge(id, challengeDTO);
        return new ResponseEntity<>(attend, HttpStatus.OK);
    }
}
