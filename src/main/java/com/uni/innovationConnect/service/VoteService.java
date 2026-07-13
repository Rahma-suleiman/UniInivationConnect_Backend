package com.uni.innovationConnect.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.VoteDTO;
import com.uni.innovationConnect.dto.VoteResponseDTO;
import com.uni.innovationConnect.enums.IdeaStatus;
import com.uni.innovationConnect.model.Idea;
import com.uni.innovationConnect.model.Role;
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

        // Get all votes
        public List<VoteDTO> getAllVotes() {

                return voteRepository.findAll()
                                .stream()
                                .map(this::mapToDTO)
                                .collect(Collectors.toList());

        }

        // Get vote by id
        public VoteDTO getVoteById(Long id) {

                Vote vote = voteRepository.findById(id)
                                .orElseThrow(() -> new IllegalStateException("Vote not found"));

                return mapToDTO(vote);

        }

        // Create vote
        public VoteDTO createVote(VoteDTO dto) {

                if (dto.getIdeaId() == null || dto.getUserId() == null) {

                        throw new IllegalStateException(
                                        "Idea and user are required");
                }

                Idea idea = ideaRepository.findById(dto.getIdeaId())

                                .orElseThrow(() -> new IllegalStateException(
                                                "Idea not found"));

                User user = userRepository.findById(dto.getUserId())

                                .orElseThrow(() -> new IllegalStateException(
                                                "User not found"));

                if (user.getRole() != Role.STUDENT) {

                        throw new IllegalStateException(
                                        "Only students can vote");

                }

                if (idea.getUser().getId()
                                .equals(user.getId())) {

                        throw new IllegalStateException(
                                        "You cannot vote your own idea");

                }

                if (idea.getStatus() != IdeaStatus.PENDING
                                &&
                                idea.getStatus() != IdeaStatus.UNDER_REVIEW) {

                        throw new IllegalStateException(
                                        "Voting is closed for this idea");

                }

                if (voteRepository.existsByIdeaIdAndUserId(
                                idea.getId(),
                                user.getId())) {

                        throw new IllegalStateException(
                                        "You already voted for this idea");

                }

                Vote vote = new Vote();

                vote.setIdea(idea);

                vote.setUser(user);

                return mapToDTO(
                                voteRepository.save(vote));

        }

        // Get votes by idea
        public List<VoteResponseDTO> getVotesByIdea(Long ideaId) {

                return voteRepository.findByIdeaId(ideaId)
                                .stream()
                                .map(this::mapToResponseDTO)
                                .collect(Collectors.toList());

        }

        // Delete vote
        public void deleteVote(Long id) {

                Vote vote = voteRepository.findById(id)
                                .orElseThrow(() -> new IllegalStateException("Vote not found"));

                voteRepository.delete(vote);

        }

        // Entity -> DTO
        private VoteDTO mapToDTO(Vote vote) {

                VoteDTO dto = new VoteDTO();

                dto.setId(vote.getId());

                if (vote.getIdea() != null) {
                        dto.setIdeaId(vote.getIdea().getId());
                }

                if (vote.getUser() != null) {
                        dto.setUserId(vote.getUser().getId());
                }

                return dto;

        }

        private VoteResponseDTO mapToResponseDTO(Vote vote) {

                VoteResponseDTO dto = new VoteResponseDTO();

                dto.setId(vote.getId());

                if (vote.getUser() != null) {

                        dto.setStudentName(
                                        vote.getUser().getFirstName()
                                                        + " "
                                                        + vote.getUser().getLastName());

                }

                if (vote.getIdea() != null) {

                        dto.setIdeaTitle(
                                        vote.getIdea().getTitle());

                }

                return dto;

        }

}

// Student 7 votes for Student 5's idea
// {
// "ideaId": 1,
// "userId": 7
// }
// Student 5 votes for Student 6's idea
// {
// "ideaId": 2,
// "userId": 5
// }
// Student 7 votes for Student 6's idea
// {
// "ideaId": 2,
// "userId": 7
// }
// Student 5 votes for Student 7's idea
// {
// "ideaId": 3,
// "userId": 5
// }
// Student 6 votes for Student 7's idea
// {
// "ideaId": 3,
// "userId": 6
// }