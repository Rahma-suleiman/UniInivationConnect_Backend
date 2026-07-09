package com.uni.innovationConnect.repository;

// import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// import com.uni.innovationConnect.dto.CommentDTO;
import com.uni.innovationConnect.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // same as SELECT * FROM comments WHERE idea_id = ?;
    List<Comment> findByIdeaId(Long ideaId);
    
}
