package com.uni.innovationConnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.VoteDTO;
import com.uni.innovationConnect.repository.VoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;

    public List<VoteDTO> getAllVotes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllVotes'");
    }

    public VoteDTO getVoteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVoteById'");
    }

    public VoteDTO createVote(VoteDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createVote'");
    }

    public VoteDTO editVote(Long id, VoteDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editVote'");
    }

    public void deleteVote(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteVote'");
    }
}
