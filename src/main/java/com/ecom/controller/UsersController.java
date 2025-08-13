package com.ecom.controller;

import com.ecom.entity.Users;
import com.ecom.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    // Create User
    @PostMapping
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
        return ResponseEntity.ok(usersService.createUser(user));
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @Valid @RequestBody Users userDetails) {
        return ResponseEntity.ok(usersService.updateUser(id, userDetails));
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
