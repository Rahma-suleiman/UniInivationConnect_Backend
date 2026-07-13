package com.uni.innovationConnect.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CommentDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String message;

    private Long ideaId;

    private Long userId;
}
