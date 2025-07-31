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

import com.shiwansh.model.Role;
import com.shiwansh.repository.RoleRepo;

@RestController
@RequestMapping("/api/Role")
public class RoleController {
	@Autowired
	private RoleRepo roleRepo;
	@GetMapping
    public ResponseEntity<?> getAllRoles() {
        List<Role> roles = roleRepo.findAll();
        return ResponseEntity.ok(Map.of("data", roles, "Status", "200"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable int id) {
        Optional<Role> role = roleRepo.findById(id);
        return ResponseEntity.ok(Map.of("data", role.orElse(null), "Status", "200"));
    }

    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody Role role) {
    	roleRepo.save(role);
        return ResponseEntity.status(201).body(Map.of("data", "Role Created Successfully!", "Status", "201"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable int id, @RequestBody Role role) {
        role.setId(id);
        roleRepo.save(role);
        return ResponseEntity.status(201).body(Map.of("data", "Role Updated Successfully!", "Status", "201"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
    	roleRepo.deleteById(id);
        return ResponseEntity.status(204).body(Map.of("data", "Role Deleted Successfully!", "Status", "204"));
    }
}
