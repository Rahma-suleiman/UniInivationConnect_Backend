package com.uni.innovationConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.innovationConnect.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    
}
