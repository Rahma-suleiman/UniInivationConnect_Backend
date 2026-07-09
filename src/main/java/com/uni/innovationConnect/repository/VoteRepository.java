package com.uni.innovationConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.innovationConnect.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{

    boolean existsByIdeaIdAndUserId(Long ideaId, Long userId);


    // Get all votes for a specific idea
    List<Vote> findByIdeaId(Long ideaId);

}