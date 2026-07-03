package com.uni.innovationConnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.ChallengeDTO;
import com.uni.innovationConnect.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public List<ChallengeDTO> getAllChallenges() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllChallenges'");
    }

    public ChallengeDTO getChallengeById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChallengeById'");
    }

    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createChallenge'");
    }

    public void deleteChallenge(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteChallenge'");
    }

    public ChallengeDTO editChallenge(Long id, ChallengeDTO challengeDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editChallenge'");
    }
    
}
