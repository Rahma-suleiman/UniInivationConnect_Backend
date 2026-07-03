package com.uni.innovationConnect.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "challenges")
@Entity
public class Challenge extends AuditModel<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String sector;

    @ManyToOne
    private User user;

}