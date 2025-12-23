package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder; // This requires the security starter
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection as per project rules
    public UserServiceimpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        // Rule: Exception message must contain "exists"
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("User email already exists");
        }

        // Rule: Default role to "AGENT"
        if (user.getRole() == null) {
            user.setRole("AGENT");
        }

        // Rule: Store as BCrypt hash
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        // Rule: Exception message must contain "not"
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}