package com.uni.innovationConnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.FeedbackDTO;
import com.uni.innovationConnect.repository.FeedbackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    
    private final FeedbackRepository feedbackRepository;

    public List<FeedbackDTO> getAllFeedbacks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllFeedbacks'");
    }

    public FeedbackDTO getFeedbackById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFeedbackById'");
    }

    public FeedbackDTO createFeedback(FeedbackDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFeedback'");
    }

    public FeedbackDTO editFeedback(Long id, FeedbackDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editFeedback'");
    }

    public void deleteFeedback(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFeedback'");
    }
}
