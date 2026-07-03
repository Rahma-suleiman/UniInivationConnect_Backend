package com.uni.innovationConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.innovationConnect.model.Idea;

public interface IdeaRepository extends JpaRepository<Idea, Long> {
    
}
