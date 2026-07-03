package com.uni.innovationConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.innovationConnect.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
