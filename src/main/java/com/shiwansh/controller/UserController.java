package com.shiwansh.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiwansh.model.User;
import com.shiwansh.repository.UserRepo;

@RestController
@RequestMapping("/api/User")
public class UserController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(Map.of("data", users, "Status", "200"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        Optional<User> user = userRepo.findById(id);
        return ResponseEntity.ok(Map.of("data", user.orElse(null), "Status", "200"));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
    	// Encrypt the password using BCrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    	userRepo.save(user);
        return ResponseEntity.status(201).body(Map.of("data", "User Created Successfully!", "Status", "201"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> existingUserOpt = userRepo.findById(id);
        if (existingUserOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found", "Status", "404"));
        }

        User existingUser = existingUserOpt.get();

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setMobile(user.getMobile());

        // Only update the password if it's provided and not blank
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepo.save(existingUser);

        return ResponseEntity.status(200).body(Map.of("data", "User Updated Successfully!", "Status", "200"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
    	userRepo.deleteById(id);
        return ResponseEntity.status(204).body(Map.of("data", "User Deleted Successfully!", "Status", "204"));
    }
	
}
