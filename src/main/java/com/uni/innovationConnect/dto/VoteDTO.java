package com.uni.innovationConnect.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VoteDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Long ideaId;

    private Long userId;
}
