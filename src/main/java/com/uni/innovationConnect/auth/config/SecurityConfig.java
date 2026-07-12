package com.uni.innovationConnect.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    // Encrypt password
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {


        http

            // Disable CSRF
            .csrf(csrf -> csrf.disable())


            // Allow all endpoints
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/api/auth/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                )
                .permitAll()

                .anyRequest()
                .permitAll()

            )


            // Remove Spring Security login form
            .formLogin(form -> form.disable())


            // Remove browser username/password popup
            .httpBasic(basic -> basic.disable());


        return http.build();

    }

}