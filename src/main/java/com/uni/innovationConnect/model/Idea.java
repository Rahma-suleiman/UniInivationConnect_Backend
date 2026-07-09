package com.uni.innovationConnect.model;

// import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.uni.innovationConnect.enums.IdeaCategory;
import com.uni.innovationConnect.enums.IdeaStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ideas")
public class Idea extends AuditModel<String> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdeaCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdeaStatus status;


    // frwd/ fk r/ship
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    // reverse r/ship
    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();
}