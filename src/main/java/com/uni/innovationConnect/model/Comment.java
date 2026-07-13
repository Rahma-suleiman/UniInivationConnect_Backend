package com.uni.innovationConnect.model;

import jakarta.persistence.*;
// import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment extends AuditModel<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;


    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
