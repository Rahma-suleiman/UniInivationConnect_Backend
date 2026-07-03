package com.uni.innovationConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.innovationConnect.model.Challenge;


public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    
}
