package com.uni.innovationConnect.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.FeedbackDTO;
import com.uni.innovationConnect.model.Feedback;
import com.uni.innovationConnect.model.Idea;
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

                .orElseThrow(() ->
                        new IllegalStateException(
                                "Feedback not found"
                        ));


        return mapToDTO(feedback);

    }







    // Create feedback
    public FeedbackDTO createFeedback(FeedbackDTO dto) {


        Feedback feedback = new Feedback();


        feedback.setComment(dto.getComment());



        // Find idea
        Idea idea = ideaRepository.findById(dto.getIdea())

                .orElseThrow(() ->
                        new IllegalStateException(
                                "Idea not found"
                        ));


        // Find lecturer
        User lecturer = userRepository.findById(dto.getLecturer())

                .orElseThrow(() ->
                        new IllegalStateException(
                                "Lecturer not found"
                        ));



        feedback.setIdea(idea);

        feedback.setLecturer(lecturer);



        Feedback savedFeedback =
                feedbackRepository.save(feedback);



        return mapToDTO(savedFeedback);

    }




    // Update feedback
    public FeedbackDTO editFeedback(
            Long id,
            FeedbackDTO dto) {


        Feedback feedback = feedbackRepository.findById(id)

                .orElseThrow(() ->
                        new IllegalStateException(
                                "Feedback not found"
                        ));



        feedback.setComment(dto.getComment());



        Feedback updatedFeedback =
                feedbackRepository.save(feedback);



        return mapToDTO(updatedFeedback);

    }








    // Delete feedback
    public void deleteFeedback(Long id) {


        Feedback feedback = feedbackRepository.findById(id)

                .orElseThrow(() ->
                        new IllegalStateException(
                                "Feedback not found"
                        ));



        feedbackRepository.delete(feedback);

    }





    public List<FeedbackDTO> getFeedbackByIdea(Long ideaId){

    return feedbackRepository.findByIdeaId(ideaId)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());

}



    // Entity to DTO
    private FeedbackDTO mapToDTO(Feedback feedback) {


        FeedbackDTO dto =
                modelMapper.map(
                        feedback,
                        FeedbackDTO.class
                );



        // Foreign key mapping

        if(feedback.getIdea() != null){

            dto.setIdea(
                    feedback.getIdea().getId()
            );

        }



        if(feedback.getLecturer() != null){

            dto.setLecturer(
                    feedback.getLecturer().getId()
            );

        }



        return dto;

    }

}