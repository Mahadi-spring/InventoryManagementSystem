package com.mahadi.InventoryManagementSystem.controller;

import com.mahadi.InventoryManagementSystem.dto.LoginRequestDTO;
import com.mahadi.InventoryManagementSystem.dto.RegisterRequestDTO;
import com.mahadi.InventoryManagementSystem.dto.Response;
import com.mahadi.InventoryManagementSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

 @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid RegisterRequestDTO registerRequestDTO){
     return ResponseEntity.ok((userService.registerUser(registerRequestDTO)));
 }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok((userService.loginUser(loginRequestDTO)));
    }
}
