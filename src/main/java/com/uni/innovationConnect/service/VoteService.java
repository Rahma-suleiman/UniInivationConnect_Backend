package com.uni.innovationConnect.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.VoteDTO;
import com.uni.innovationConnect.model.Idea;
import com.uni.innovationConnect.model.User;
import com.uni.innovationConnect.model.Vote;
import com.uni.innovationConnect.repository.IdeaRepository;
import com.uni.innovationConnect.repository.UserRepository;
import com.uni.innovationConnect.repository.VoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoteService {

        private final VoteRepository voteRepository;

        private final IdeaRepository ideaRepository;

        private final UserRepository userRepository;

        private final ModelMapper modelMapper;

        // Create vote
        public VoteDTO createVote(VoteDTO dto) {

                Idea idea = ideaRepository.findById(dto.getIdea())

                                .orElseThrow(() -> new IllegalStateException(
                                                "Idea not found"));

                User user = userRepository.findById(dto.getUser())

                                .orElseThrow(() -> new IllegalStateException(
                                                "User not found"));

                // Only students can vote

                if (!user.getRole()
                                .name()
                                .equals("STUDENT")) {

                        throw new IllegalStateException(
                                        "Only students can vote");

                }

                // Cannot vote rejected ideas

                if (idea.getStatus()
                                .name()
                                .equals("REJECTED")) {

                        throw new IllegalStateException(
                                        "Cannot vote rejected idea");

                }

                boolean alreadyVoted = voteRepository
                                .existsByIdeaIdAndUserId(
                                                idea.getId(),
                                                user.getId());

                if (alreadyVoted) {

                        throw new IllegalStateException(
                                        "You already voted for this idea");

                }

                Vote vote = new Vote();

                vote.setIdea(idea);

                vote.setUser(user);

                Vote saved = voteRepository.save(vote);

                return mapToDTO(saved);

        }

        // Get votes of an idea

        public List<VoteDTO> getVotesByIdea(Long ideaId) {

                return voteRepository
                                .findByIdeaId(ideaId)

                                .stream()

                                .map(this::mapToDTO)

                                .collect(Collectors.toList());

        }

        // Delete vote (student removes vote)

        public void deleteVote(Long id) {

                Vote vote = voteRepository.findById(id)

                                .orElseThrow(() -> new IllegalStateException(
                                                "Vote not found"));

                voteRepository.delete(vote);

        }

        private VoteDTO mapToDTO(Vote vote) {

                VoteDTO dto = modelMapper.map(
                                vote,
                                VoteDTO.class);

                dto.setIdea(
                                vote.getIdea().getId());

                dto.setUser(
                                vote.getUser().getId());

                return dto;

        }

}