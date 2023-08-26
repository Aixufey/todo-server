package com.todoap.todoapi.todo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Filter chain for basic authentication.
 * - Authenticate all request
 * - basic authentication
 * - disable CSRF
 * - stateless REST API
 */
@Configuration
@EnableWebSecurity
public class BasicAuthenticationSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        http.httpBasic(withDefaults());

        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
