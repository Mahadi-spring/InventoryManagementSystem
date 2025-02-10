package com.mahadi.InventoryManagementSystem.controller;

import com.mahadi.InventoryManagementSystem.dto.LoginRequestDTO;
import com.mahadi.InventoryManagementSystem.dto.RegisterRequestDTO;
import com.mahadi.InventoryManagementSystem.dto.Response;
import com.mahadi.InventoryManagementSystem.dto.UserDTO;
import com.mahadi.InventoryManagementSystem.entity.User;
import com.mahadi.InventoryManagementSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllUsers (){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateUser (@PathVariable Long id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping ("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteUser (@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("/transactions/{userid}")
    public ResponseEntity<Response> getUserAndTransactions(@PathVariable Long userid){
        return ResponseEntity.ok(userService.getUserTransactions(userid));
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentLoggedInUser());
    }
}
