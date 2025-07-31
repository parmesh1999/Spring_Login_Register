package com.shiwansh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiwansh.DTO.AuthResponse;
import com.shiwansh.DTO.LoginDTO;
import com.shiwansh.DTO.SignupDTO;
import com.shiwansh.model.Role;
import com.shiwansh.model.User;
import com.shiwansh.model.UserRole;
import com.shiwansh.repository.RoleRepo;
import com.shiwansh.repository.UserRepo;
import com.shiwansh.repository.UserRoleRepo;
import com.shiwansh.security.JwtTokenProvider;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private JwtTokenProvider jwtUtil;

    public AuthResponse authenticate(LoginDTO model) {
        User user = userRepo.findByEmail(model.getUsername());

        if (user == null || !BCrypt.checkpw(model.getPassword(), user.getPassword())) {
            AuthResponse resp = new AuthResponse();
            resp.setStatusCode("401");
            return resp;
        }

        UserRole userRole = userRoleRepo.findByUserId(user.getId());
        if (userRole == null) {
            AuthResponse resp = new AuthResponse();
            resp.setStatusCode("403");
            return resp;
        }

        Role role = roleRepo.findById(userRole.getRoleId()).orElse(null);
        String token = jwtUtil.generateToken(user.getEmail());

        AuthResponse response = new AuthResponse();
        response.setUser(user);
        response.setRole(role);
        response.setToken(token);
        response.setStatusCode("200");
        return response;
    }

    public String register(SignupDTO model) {
        if (userRepo.findByEmail(model.getEmail()) != null) {
            return "User with this email already exists.";
        }

        User user = new User();
        user.setName(model.getName());
        user.setEmail(model.getEmail());
        user.setMobile(model.getMobile());
        user.setPassword(BCrypt.hashpw(model.getPassword(), BCrypt.gensalt()));

        userRepo.save(user);
        return "User registered SUCCESSFULLY.";
    }
}

