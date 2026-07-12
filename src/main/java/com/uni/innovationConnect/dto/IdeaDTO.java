package com.uni.innovationConnect.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uni.innovationConnect.enums.IdeaCategory;
import com.uni.innovationConnect.enums.IdeaStatus;

import lombok.Data;

@Data
public class IdeaDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    
    private String title;

    private String description;

    private IdeaCategory category;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private IdeaStatus status;

    private Long userId;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> commentIds;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> feedbackIds;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> voteIds;
}
