package com.sit331.artgallery.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatchers;

import com.google.cloud.storage.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private FirebaseAuthenticationFilter firebaseAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                				.requestMatchers(new AntPathRequestMatcher("/api/**", "POST")).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/api/**", "PUT")).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/api/**", "DELETE")).authenticated()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/**", "GET")).permitAll()
                )
                .addFilterBefore(firebaseAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
