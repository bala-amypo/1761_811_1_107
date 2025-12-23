package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection only (no @Autowired on fields)
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        // Rule: Check if email exists; throw exception with message containing "exists"
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("User with this email already exists");
        }

        // Rule: Default role to "AGENT" if null
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("AGENT");
        }

        // Rule: Password must be stored as BCrypt hash
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        // Rule: Throw exception with message containing "not" if not found
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
}