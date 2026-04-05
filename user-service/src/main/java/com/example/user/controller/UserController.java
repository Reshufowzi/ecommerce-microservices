package com.example.user.controller;

import com.example.user.entity.User;
import com.example.user.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repo.save(user);
    }

    @PostMapping("/login")
public String login(@RequestBody User user) {
    User dbUser = repo.findByEmail(user.getEmail());
    if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
        return "Login successful for user: " + user.getEmail();
    }
    return "Invalid Credentials";
}
}
