package com.example.car.controllers;

import com.example.car.dto.UserRequestDTO;
import com.example.car.entities.ThanhVien;
import com.example.car.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<ThanhVien> login(@RequestBody UserRequestDTO userRequest) {
        ThanhVien thanhVien = authService.login(userRequest);
        if (thanhVien != null) {
            return ResponseEntity.ok(thanhVien);
        } else {
            return ResponseEntity.status(401).body(null); // Unauthorized
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ThanhVien> register(@RequestBody UserRequestDTO userRequest) {
        ThanhVien thanhVien = authService.register(userRequest);
        if (thanhVien != null) {
            return ResponseEntity.ok(thanhVien);
        } else {
            return ResponseEntity.status(400).body(null); // Bad Request
        }
    }
}
