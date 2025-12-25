package com.example.demo.service.impl;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    public UserServiceImpl(UserRepository r) { this.repo = r; }

    public User register(User u) {
        if(repo.existsByEmail(u.getEmail())) throw new RuntimeException("Email exists");
        return repo.save(u);
    }
    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Not found"));
    }
}