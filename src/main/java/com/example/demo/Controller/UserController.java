package com.example.demo.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	 @Autowired
	    private UserService service;

	    Logger log = LoggerFactory.getLogger(UserController.class);

	    @PostMapping("/register")
	    public ResponseEntity<User> register(@RequestBody User user) {
	        User createdUser = service.register(user);
	        log.info("User registered successfully");
	        return ResponseEntity.ok(createdUser);
	    }

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody User user) {
	        boolean success = service.login(user.getUsername(), user.getPassword());
	        log.warn("you have entered correct crediantls");
	        if (success) {
	            return ResponseEntity.ok("Login successful");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	        }
	    }

	    @GetMapping("/getAll")
	    public List<User> getAllUsers() {
	        return service.getAllUsers();
	    }
}
