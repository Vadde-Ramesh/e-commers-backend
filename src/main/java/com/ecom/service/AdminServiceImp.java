package com.ecom.service;

import com.ecom.entity.Admin;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.repository.AdminRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder password;

    // Create Admin
    public Admin save(Admin admin) {
    	admin.setRole("ROLE_ADMIN");
    	admin.setPassword(password.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    // Get All Admins
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    // Get Admin by ID
    public Admin findById(Integer id) {
        return adminRepository.findById(id).get();
    }

    // Update Admin
    public Admin updateAdmin(Integer id, Admin adminDetails) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));

        admin.setName(adminDetails.getName());
        admin.setEmail(adminDetails.getEmail());
        admin.setUserName(adminDetails.getUsername());
        admin.setPassword(adminDetails.getPassword());
        return adminRepository.save(admin);
    }

    // Delete Admin
    public void deleteById(Integer id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));

        adminRepository.delete(admin);
    }


}
