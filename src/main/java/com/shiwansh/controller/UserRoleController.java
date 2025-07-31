package com.shiwansh.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiwansh.model.UserRole;
import com.shiwansh.repository.UserRoleRepo;

@RestController
@RequestMapping("/api/UserRole")
public class UserRoleController {
	@Autowired
	private UserRoleRepo userRolerepo;
	
	@GetMapping
    public ResponseEntity<?> getAllUserRoles() {
        List<UserRole> roles = userRolerepo.findAll();
        return ResponseEntity.ok(Map.of("data", roles, "Status", "200"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserRoleById(@PathVariable int id) {
        Optional<UserRole> userRole = userRolerepo.findById(id);
        return ResponseEntity.ok(Map.of("data", userRole.orElse(null), "Status", "200"));
    }

    @PostMapping
    public ResponseEntity<?> addUserRole(@RequestBody UserRole userRole) {
    	userRolerepo.save(userRole);
        return ResponseEntity.status(201).body(Map.of("data", "UserRole Added Successfully!", "Status", "201"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserRole(@PathVariable int id, @RequestBody UserRole userRole) {
        userRole.setId(id);
        userRolerepo.save(userRole);
        return ResponseEntity.status(201).body(Map.of("data", "UserRole Updated Successfully!", "Status", "201"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserRole(@PathVariable int id) {
    	userRolerepo.deleteById(id);
        return ResponseEntity.status(204).body(Map.of("data", "UserRole Deleted!", "Status", "204"));
    }

}
