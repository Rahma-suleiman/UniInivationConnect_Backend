package com.uni.innovationConnect.enums;

public enum IdeaStatus {

    PENDING,        // Student has submitted a new idea

    UNDER_REVIEW,   // Lecturer/stakeholder is evaluating the idea

    APPROVED,       // Idea accepted for development

    REJECTED,       // Idea declined after review

    IMPLEMENTED     // Idea has been developed into a solution
}

// Student posts idea
//         ↓
//      PENDING
//         ↓
// Lecturer starts review
//         ↓
//    UNDER_REVIEW
//       /       \
//      /         \
// APPROVED     REJECTED