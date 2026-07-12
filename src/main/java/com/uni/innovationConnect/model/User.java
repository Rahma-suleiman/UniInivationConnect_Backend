package com.uni.innovationConnect.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User extends AuditModel<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;


    private String lastName;


    @Column(unique = true, nullable = false)
    private String email;


    @Column(nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;


    // User -> Ideas
    @OneToMany(mappedBy = "user")
    private List<Idea> ideas = new ArrayList<>();


    // User -> Comments
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();


    // User -> Votes
    @OneToMany(mappedBy = "user")
    private List<Vote> votes = new ArrayList<>();


    // User -> Challenges
    @OneToMany(mappedBy = "user")
    private List<Challenge> challenges = new ArrayList<>();


    // Lecturer -> Feedback
    @OneToMany(mappedBy = "lecturer")
    private List<Feedback> feedbacks = new ArrayList<>();

}