package com.uni.innovationConnect.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VoteDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Long idea;

    private Long user;
}
// NB: RERVESE HAS TO HV READ_ONLY PROPERTY JXT LYK ID
