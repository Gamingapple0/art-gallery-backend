package com.sit331.artgallery.auth;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatchers;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.DispatcherServlet;

import com.google.cloud.storage.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private FirebaseAuthenticationFilter firebaseAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Enable CORS explicitly
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(new AntPathRequestMatcher("/api/**", "POST")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/**", "PUT")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/**", "DELETE")).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/api/**", "GET")).permitAll()
            )
            .addFilterBefore(firebaseAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setDispatchOptionsRequest(true);
        return dispatcherServlet;
    }
    
    @Bean
    public StrictHttpFirewall httpFirewall() {

        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "OPTIONS", "FOO"));

        return firewall;
    }

}
