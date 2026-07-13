package com.uni.innovationConnect.model;

import jakarta.persistence.*;
// import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "feedbacks")
public class Feedback extends AuditModel<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    // fk
    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private User lecturer;

}