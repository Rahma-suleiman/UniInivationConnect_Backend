package com.uni.innovationConnect.auth.dto;



import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AuthResponse {


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private String message;

}
// {
//  "id":1,
//  "firstName":"Rahma",
//  "lastName":"Ali",
//  "email":"rahma@gmail.com",
//  "role":"STUDENT",
//  "message":"Login successful"
// }
// if(user.role==="STUDENT"){
//    openStudentDashboard();
// }


// if(user.role==="LECTURER"){
//    openLecturerDashboard();
// }