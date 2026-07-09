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




    // Get all votes
    public List<VoteDTO> getAllVotes(){


        return voteRepository.findAll()

                .stream()

                .map(this::mapToDTO)

                .collect(Collectors.toList());

    }





    // Get vote by id
    public VoteDTO getVoteById(Long id){


        Vote vote = voteRepository.findById(id)

                .orElseThrow(() ->
                        new IllegalStateException(
                                "Vote not found"
                        ));


        return mapToDTO(vote);

    }





    // Create vote
    public VoteDTO createVote(VoteDTO dto){


        // Check duplicate vote

        boolean alreadyVoted =
                voteRepository.existsByIdeaIdAndUserId(
                        dto.getIdea(),
                        dto.getUser()
                );


        if(alreadyVoted){

            throw new IllegalStateException(
                    "User already voted for this idea"
            );

        }




        Vote vote = new Vote();



        Idea idea = ideaRepository.findById(dto.getIdea())

                .orElseThrow(() ->
                        new IllegalStateException(
                                "Idea not found"
                        ));



        User user = userRepository.findById(dto.getUser())

                .orElseThrow(() ->
                        new IllegalStateException(
                                "User not found"
                        ));



        vote.setIdea(idea);

        vote.setUser(user);



        Vote savedVote =
                voteRepository.save(vote);



        return mapToDTO(savedVote);

    }






    // Update vote
    public VoteDTO editVote(Long id, VoteDTO dto){


        Vote vote = voteRepository.findById(id)

                .orElseThrow(() ->
                        new IllegalStateException(
                                "Vote not found"
                        ));



        Idea idea = ideaRepository.findById(dto.getIdea())

                .orElseThrow(() ->
                        new IllegalStateException(
                                "Idea not found"
                        ));



        User user = userRepository.findById(dto.getUser())

                .orElseThrow(() ->
                        new IllegalStateException(
                                "User not found"
                        ));



        vote.setIdea(idea);

        vote.setUser(user);



        Vote updatedVote =
                voteRepository.save(vote);



        return mapToDTO(updatedVote);

    }







    // Delete vote
    public void deleteVote(Long id){


        Vote vote = voteRepository.findById(id)

                .orElseThrow(() ->
                        new IllegalStateException(
                                "Vote not found"
                        ));


        voteRepository.delete(vote);

    }



// Get votes by idea
public List<VoteDTO> getVotesByIdea(Long ideaId){

    return voteRepository.findByIdeaId(ideaId)

            .stream()

            .map(this::mapToDTO)

            .collect(Collectors.toList());

}


    // Entity -> DTO
    private VoteDTO mapToDTO(Vote vote){


        VoteDTO dto =
                modelMapper.map(
                        vote,
                        VoteDTO.class
                );



        if(vote.getIdea()!=null){

            dto.setIdea(
                    vote.getIdea().getId()
            );

        }



        if(vote.getUser()!=null){

            dto.setUser(
                    vote.getUser().getId()
            );

        }



        return dto;

    }

}