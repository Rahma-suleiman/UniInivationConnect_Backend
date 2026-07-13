package com.uni.innovationConnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uni.innovationConnect.dto.FeedbackDTO;
import com.uni.innovationConnect.service.FeedbackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/innovationConnect/feedback")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        List<FeedbackDTO> feedbacks = feedbackService.getAllFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) {
        FeedbackDTO feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

//     Since feedback belongs to an idea, add:
// GET /api/v2/innovationConnect/feedback/idea/{ideaId}
// Example: GET /api/v2/innovationConnect/feedback/idea/1
    @GetMapping("/idea/{ideaId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByIdea(
            @PathVariable Long ideaId) {

        return ResponseEntity.ok(
                feedbackService.getFeedbackByIdea(ideaId));

    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO dto) {
        FeedbackDTO feedback = feedbackService.createFeedback(dto);
        // return new ResponseEntity<>(feedback, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedback);

    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> editFeedback(@PathVariable Long id, @RequestBody FeedbackDTO dto) {
        FeedbackDTO feedback = feedbackService.editFeedback(id, dto);
        return ResponseEntity.ok(feedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

}
