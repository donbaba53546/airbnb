package com.bnb.airbnb.configuration;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfiguration {
    private JWTFilter jwtFilter;

    public SecurityConfiguration(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    //builtin class
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        // h(cd)2(square)
        http.csrf().disable().cors().disable();
        //this disables csrf attack means allow third party application interaction
        //haap
        http.authorizeHttpRequests().anyRequest().permitAll();
        //any http request in the project permit all

        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/authentication/createPROPERTYOWNER","/api/authentication/createuser")
//                .permitAll()
//                .requestMatchers("/api/property/addproperty").hasAnyRole("OWNER","ADMIN")
//                .requestMatchers("/api/authentication/createPROPERTYMANAGER").hasRole("ADMIN")
//                .anyRequest().authenticated();
        return http.build();//here build create the object on current information
    }
}
