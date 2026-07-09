package com.uni.innovationConnect.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.IdeaDTO;
import com.uni.innovationConnect.enums.IdeaStatus;
import com.uni.innovationConnect.model.Idea;
import com.uni.innovationConnect.model.User;
import com.uni.innovationConnect.repository.IdeaRepository;
import com.uni.innovationConnect.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdeaService {

    private final IdeaRepository ideaRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // Get all ideas
    public List<IdeaDTO> getAllIdeas() {
        return ideaRepository.findAll()
                .stream()
                // .map(this::mapToDTO)
                .map(idea -> mapToDTO(idea))
                .collect(Collectors.toList());
    }

    // Get idea by id
    public IdeaDTO getIdeaById(Long id) {
        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Idea not found"));

        return mapToDTO(idea);
    }

    // Step 1: Student submits an idea
    public IdeaDTO createIdea(IdeaDTO dto) {

        Idea idea = new Idea();

        idea.setTitle(dto.getTitle());

        idea.setDescription(dto.getDescription());

        idea.setCategory(dto.getCategory());

        idea.setStatus(IdeaStatus.PENDING);

        // Find the idea owner
        User user = userRepository.findById(dto.getUser())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        // Connect user with idea
        idea.setUser(user);

        Idea savedIdea = ideaRepository.save(idea);

        return mapToDTO(savedIdea);
    }

    // Update idea
    public IdeaDTO editIdea(Long id, IdeaDTO dto) {

        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Idea not found"));

        idea.setTitle(dto.getTitle());
        idea.setDescription(dto.getDescription());
        idea.setCategory(dto.getCategory());

        // Allow status update by lecturer/admin
        if (dto.getStatus() != null) {
            idea.setStatus(dto.getStatus());
        }

        Idea updatedIdea = ideaRepository.save(idea);

        return mapToDTO(updatedIdea);

    }

    // Delete idea
    public void deleteIdea(Long id) {
        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Idea not found"));

        ideaRepository.delete(idea);

    }

    // Convert Entity to DTO
    private IdeaDTO mapToDTO(Idea idea) {

        IdeaDTO dto = modelMapper.map(idea, IdeaDTO.class);

        // fk
        if (idea.getUser() != null) {
            dto.setUser(idea.getUser().getId());
        }

        // reverse r/ship
        dto.setCommentIds(
                idea.getComments()
                        .stream()
                        .map(comment -> comment.getId())
                        .collect(Collectors.toList()));

        dto.setFeedbackIds(
                idea.getFeedbacks()
                        .stream()
                        .map(feedback -> feedback.getId())
                        .collect(Collectors.toList()));

        dto.setVoteIds(
                idea.getVotes()
                        .stream()
                        .map(vote -> vote.getId())
                        .collect(Collectors.toList()));

        return dto;

    }

    // (Step 2:Lecturer Reviews Idea)Lecturer updates idea status
    // Purpose:Allows lecturer/admin to change the idea lifecycle status.
    public IdeaDTO updateIdeaStatus(Long id, IdeaStatus status) {

        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Idea not found"));

        // Status workflow validation
        // When the lecturer changes status: idea.setStatus(status);
        // before saving, you call:if(!isValidStatusTransition(idea.getStatus(),status)){
        // Example:
        // Database:Idea ID: 1,Current status: PENDING
        // Lecturer sends:PATCH /idea/1/status?status=APPROVED (which is the new status to b changed by the isValidStatusTransition())
        // The method(isValidStatusTransition()) checks: currentStatus = PENDING, newStatus = APPROVED
        // It reaches: case PENDING -> newStatus == UNDER_REVIEW || newStatus == REJECTED;
        // Checking: APPROVED == UNDER_REVIEW  ❌, APPROVED == REJECTED       ❌
        // Result:false ,The update is blocked.

        // isValidStatusTransition() returns	!result	Enters if?	 Outcome
        // true	                                false	❌ No	    Status update continues
        // false	                            true	✅ Yes	    Exception is thrown
        if (!isValidStatusTransition(idea.getStatus(), status)) {
            throw new IllegalStateException("Invalid status change from " + idea.getStatus() + " to " + status);
        }

        idea.setStatus(status);

        Idea updatedIdea = ideaRepository.save(idea);

        return mapToDTO(updatedIdea);

    }


    // isValidStatusTransition() is a security rule for your idea 
    // lifecycle. It prevents users (especially students) from 
    // skipping steps or changing an idea status incorrectly.
// This method receives two values:
// 1. currentStatus->The current status of the idea in the database.
// Example:currentStatus = IdeaStatus.PENDING;
// Meaning:The student has submitted an idea, and it is waiting for review.
// 2. newStatus->The status someone wants to change it to.
// Example:newStatus = IdeaStatus.UNDER_REVIEW;
// Meaning:A lecturer wants to start reviewing the idea.

// The method returns:
// true->if the change is allowed.
// or
// false->if the change is not allowed.
    private boolean isValidStatusTransition(IdeaStatus currentStatus,IdeaStatus newStatus) {

        // switch statement means:Check the current status and decide which status changes are allowed.
        return switch (currentStatus) {

            // If the idea is currently:PENDING, it can only move to:UNDER_REVIEW or REJECTED
            // Example:
            // Allowed: PENDING → UNDER_REVIEW ✅(because the lecturer starts evaluation.)
            // Allowed:PENDING → REJECTED ✅(because the idea can be rejected immediately)
            // Not allowed:PENDING → APPROVED ❌(because the lecturer should review it first.)
            case PENDING ->
                newStatus == IdeaStatus.UNDER_REVIEW
                        || newStatus == IdeaStatus.REJECTED;

            // If the idea is being reviewed:UNDER_REVIEW, the lecturer has two choices:
            // Approve:UNDER_REVIEW → APPROVED(The idea is good and can be developed.)
            // Reject:UNDER_REVIEW → REJECTED(The idea is not accepted.)
            // Not allowed:UNDER_REVIEW → IMPLEMENTED ❌(because the idea cannot be implemented before approval.)
            case UNDER_REVIEW ->
                newStatus == IdeaStatus.APPROVED
                        || newStatus == IdeaStatus.REJECTED;

            // Meaning:After approval, the only next step is development.
            // Flow:APPROVED → IMPLEMENTED
            // Example:The university accepts:Smart Farming System
            // The development team creates the system.
            // After completion:IMPLEMENTED
            case APPROVED ->
                newStatus == IdeaStatus.IMPLEMENTED;

            // Meaning:A rejected idea cannot move anywhere.
            // Example:REJECTED → APPROVED ❌
            // or
            // REJECTED → PENDING ❌
            // The lifecycle ends.
            case REJECTED ->
                false;

            // Meaning:The project is already completed.It cannot move again.
            // Example:IMPLEMENTED → APPROVED ❌
            // or
            // IMPLEMENTED → PENDING ❌
            case IMPLEMENTED ->
                false;

        };

    }
}
// 2. Add status transition validation

// This controls your lifecycle:

// PENDING
// |
// ▼
// UNDER_REVIEW
// |
// ▼
// APPROVED
// |
// ▼
// IMPLEMENTED