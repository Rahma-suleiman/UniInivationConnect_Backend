package com.uni.innovationConnect.auth.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.uni.innovationConnect.model.Role;
import com.uni.innovationConnect.model.User;
import com.uni.innovationConnect.repository.UserRepository;



@Configuration
public class DataInitializer {


    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {


        return args -> {


            // Check if admin already exists
            boolean adminExists =
                    userRepository.existsByEmail(
                            "admin@uic.com"
                    );


            if(!adminExists) {


                User admin = new User();


                admin.setFirstName(
                        "System"
                );


                admin.setLastName(
                        "Admin"
                );


                admin.setEmail(
                        "admin@uic.com"
                );


                admin.setPassword(
                        passwordEncoder.encode(
                                "Admin@123"
                        )
                );


                // Backend assigns role
                admin.setRole(
                        Role.ADMIN
                );


                userRepository.save(admin);



                System.out.println(
                    "Default ADMIN created successfully"
                );

                System.out.println(
                    "Email: admin@uic.com"
                );

                System.out.println(
                    "Password: Admin@123"
                );


            } else {


                System.out.println(
                    "ADMIN already exists"
                );


            }


        };


    }


}