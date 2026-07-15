package com.uni.innovationConnect.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.FeedbackDTO;
import com.uni.innovationConnect.enums.IdeaStatus;
import com.uni.innovationConnect.model.Feedback;
import com.uni.innovationConnect.model.Idea;
import com.uni.innovationConnect.model.Role;
import com.uni.innovationConnect.model.User;
import com.uni.innovationConnect.repository.FeedbackRepository;
import com.uni.innovationConnect.repository.IdeaRepository;
import com.uni.innovationConnect.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {

        private final FeedbackRepository feedbackRepository;

        private final IdeaRepository ideaRepository;

        private final UserRepository userRepository;

        private final ModelMapper modelMapper;

        // Get all feedbacks
        public List<FeedbackDTO> getAllFeedbacks() {

                return feedbackRepository.findAll()

                                .stream()

                                .map(this::mapToDTO)

                                .collect(Collectors.toList());

        }

        // Get feedback by id
        public FeedbackDTO getFeedbackById(Long id) {

                Feedback feedback = feedbackRepository.findById(id)

                                .orElseThrow(() -> new IllegalStateException(
                                                "Feedback not found"));

                return mapToDTO(feedback);

        }

        public FeedbackDTO createFeedback(FeedbackDTO dto) {

                if (dto.getComment() == null
                                || dto.getComment().isBlank()) {

                        throw new IllegalStateException(
                                        "Feedback comment is required");

                }

                Idea idea = ideaRepository.findById(dto.getIdea())

                                .orElseThrow(() -> new IllegalStateException(
                                                "Idea not found"));

                // Feedback only during review stage
                if (idea.getStatus() != IdeaStatus.UNDER_REVIEW) {

                        throw new IllegalStateException(
                                        "Feedback can only be added when idea is under review");

                }

                User lecturer = userRepository.findById(dto.getLecturer())

                                .orElseThrow(() -> new IllegalStateException(
                                                "Lecturer not found"));

                // Only lecturers can give feedback
                if (lecturer.getRole() != Role.LECTURER) {

                        throw new IllegalStateException(
                                        "Only lecturers can provide feedback");

                }

                // Prevent duplicate lecturer feedback
                boolean exists = feedbackRepository
                                .existsByIdeaIdAndLecturerId(
                                                idea.getId(),
                                                lecturer.getId());

                if (exists) {

                        throw new IllegalStateException(
                                        "You already provided feedback for this idea");

                }

                Feedback feedback = new Feedback();

                feedback.setComment(dto.getComment());

                feedback.setIdea(idea);

                feedback.setLecturer(lecturer);

                Feedback saved = feedbackRepository.save(feedback);

                return mapToDTO(saved);

        }

        // Update feedback
        public FeedbackDTO editFeedback(
                        Long id,
                        FeedbackDTO dto) {

                Feedback feedback = feedbackRepository.findById(id)

                                .orElseThrow(() -> new IllegalStateException(
                                                "Feedback not found"));

                if (dto.getLecturer() == null) {

                        throw new IllegalStateException(
                                        "Lecturer id is required");

                }

                // Check feedback owner
                if (!feedback.getLecturer()
                                .getId()
                                .equals(dto.getLecturer())) {

                        throw new IllegalStateException(
                                        "You can only edit your own feedback");

                }

                if (dto.getComment() == null
                                || dto.getComment().isBlank()) {

                        throw new IllegalStateException(
                                        "Feedback comment is required");

                }

                feedback.setComment(dto.getComment());

                Feedback updated = feedbackRepository.save(feedback);

                return mapToDTO(updated);

        }

        // Delete feedback
        public void deleteFeedback(Long id) {

                Feedback feedback = feedbackRepository.findById(id)

                                .orElseThrow(() -> new IllegalStateException(
                                                "Feedback not found"));

                feedbackRepository.delete(feedback);

        }

        public List<FeedbackDTO> getFeedbackByIdea(Long ideaId) {

                return feedbackRepository.findByIdeaId(ideaId)
                                .stream()
                                .map(this::mapToDTO)
                                .collect(Collectors.toList());

        }

        // Entity to DTO
        private FeedbackDTO mapToDTO(Feedback feedback) {

                FeedbackDTO dto = modelMapper.map(
                                feedback,
                                FeedbackDTO.class);

                // Foreign key mapping

                if (feedback.getIdea() != null) {

                        dto.setIdea(
                                        feedback.getIdea().getId());

                }

                if (feedback.getLecturer() != null) {

                        dto.setLecturer(
                                        feedback.getLecturer().getId());

                }

                return dto;

        }

}