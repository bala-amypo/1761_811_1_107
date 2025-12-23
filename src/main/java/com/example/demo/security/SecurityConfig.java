package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    // Rule: Constructor injection only
    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Requirement 8.2: Provide PasswordEncoder bean (BCryptPasswordEncoder).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Required for manual authentication in AuthController login.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Requirement 8.2: Configure HTTP security settings.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Requirement 8.2: Disable CSRF for this REST API
            .csrf(csrf -> csrf.disable())
            
            // Requirement 8.2: Use stateless session management (no HTTP session)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // Requirement 8.2: Path-based access control
            .authorizeHttpRequests(auth -> auth
                // Public paths (no authentication required)
                .requestMatchers("/auth/register", "/auth/login").permitAll()
                // Requirement 9: Allow Swagger endpoints without authentication
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                
                // Protected paths (Section 8.2: require valid JWT)
                .requestMatchers("/parcels/**", "/claims/**", "/evidence/**", "/rules/**").authenticated()
                
                // Any other request must be authenticated
                .anyRequest().authenticated()
            );

        // Requirement 8.2 & 8.4: Register JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}