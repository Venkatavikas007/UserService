package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public boolean login(String username, String rawPassword) {
        User user = repo.findByUsername(username);
        return user != null && encoder.matches(rawPassword, user.getPassword());
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
