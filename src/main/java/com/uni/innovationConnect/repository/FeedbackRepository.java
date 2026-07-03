package com.uni.innovationConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.innovationConnect.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
}
