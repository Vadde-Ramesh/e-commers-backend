package com.ecom.controller;

import com.ecom.entity.Admin;
import com.ecom.service.AdminService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Create Admin
    @PostMapping
    public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) {
        Admin savedAdmin = adminService.save(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    // Get All Admins
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminService.findAll());
    }

    // Get Admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer id) {
        Admin admin = adminService.findById(id);
        return ResponseEntity.ok(admin);
    }

    // Update Admin
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer id, @Valid @RequestBody Admin adminDetails) {
        Admin admin = adminService.findById(id);

        admin.setName(adminDetails.getName());
        admin.setEmail(adminDetails.getEmail());
        admin.setUsername(adminDetails.getUsername());
        admin.setPassword(adminDetails.getPassword());
        admin.setRole(adminDetails.getRole());

        Admin updatedAdmin = adminService.save(admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    // Delete Admin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer id) {

        adminService.deleteById(id);
        return ResponseEntity.ok("Admin deleted successfully");
    }
}

