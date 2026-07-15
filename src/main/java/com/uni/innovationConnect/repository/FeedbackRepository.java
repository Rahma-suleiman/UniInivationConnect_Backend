package com.uni.innovationConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.innovationConnect.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByIdeaId(Long ideaId);

      boolean existsByIdeaIdAndLecturerId(
            Long ideaId,
            Long lecturerId
    );
}
