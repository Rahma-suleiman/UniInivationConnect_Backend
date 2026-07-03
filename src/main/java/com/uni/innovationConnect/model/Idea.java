package com.uni.innovationConnect.model;

import java.time.LocalDateTime;

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


    private String category;


    private String status;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}