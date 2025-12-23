package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.exception.BadRequestException; 
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody User user) {
        User savedUser = userService.register(user);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        // 1. Find user
        User user = userService.findByEmail(authRequest.getEmail());

        // 2. Match password
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        // 3. Generate Token
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());

        // 4. Return Response
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return ResponseEntity.ok(response);
    }
}