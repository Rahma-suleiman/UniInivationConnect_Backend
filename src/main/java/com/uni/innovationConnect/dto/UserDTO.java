package com.uni.innovationConnect.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uni.innovationConnect.model.Role;

import lombok.Data;


@Data
public class UserDTO {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;


    private String firstName;


    private String lastName;


    private String email;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Role role;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<IdeaDTO> ideas;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CommentDTO> comments;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<VoteDTO> votes;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ChallengeDTO> challenges;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FeedbackDTO> feedbacks;


}