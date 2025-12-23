package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
// ... other imports

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for User Registration and Login")
public class AuthController {

    // ... constructor

    @Operation(summary = "Register a new user", description = "Creates a new user with AGENT role by default")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @Operation(summary = "Login user", description = "Returns a JWT token upon successful authentication")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        // ... login logic
    }
}