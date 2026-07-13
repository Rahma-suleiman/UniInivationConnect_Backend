package com.uni.innovationConnect.model;

import jakarta.persistence.*;
// import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "idea_id",
                "user_id"
        })
})
public class Vote extends AuditModel<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Idea receiving the vote
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idea_id", nullable = false)
    private Idea idea;

    // Student who voted
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}