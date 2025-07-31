package com.shiwansh.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shiwansh.DTO.AuthResponse;
import com.shiwansh.DTO.LoginDTO;
import com.shiwansh.DTO.SignupDTO;
import com.shiwansh.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto) {
        AuthResponse response = authService.authenticate(loginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDto) {
        String msg = authService.register(signupDto);

        if (msg.equals("User with this email already exists.")) {
            return ResponseEntity.badRequest().body(Map.of("Message", msg));
        }

        return ResponseEntity.ok(Map.of("Message", msg));
    }
}
