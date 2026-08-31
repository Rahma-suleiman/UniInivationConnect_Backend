package com.uni.innovationConnect.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {


    @Bean
    public CorsFilter corsFilter(){


        CorsConfiguration config = new CorsConfiguration();


        config.addAllowedOrigin("http://localhost:5173");
config.addAllowedOrigin("http://localhost:5177");

        config.addAllowedMethod("*");

        config.addAllowedHeader("*");


        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();


        source.registerCorsConfiguration(
                "/**",
                config
        );


        return new CorsFilter(source);

    }

}
// package com.uni.innovationConnect.auth.config;


// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.*;

// @Configuration
// public class CorsConfig {


//     @Bean
//     public WebMvcConfigurer corsConfigurer(){

//         return new WebMvcConfigurer() {

//             @Override
//             public void addCorsMappings(
//                     CorsRegistry registry) {

//                 registry.addMapping("/**")
//                         .allowedOrigins(
//                             "http://localhost:5173"
//                         )
//                         .allowedMethods(
//                             "GET",
//                             "POST",
//                             "PUT",
//                             "DELETE"
//                         )
//                         .allowedHeaders("*");

//             }

//         };
//     }
// }
