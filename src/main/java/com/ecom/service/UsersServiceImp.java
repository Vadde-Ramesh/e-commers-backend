package com.ecom.service;

import com.ecom.entity.Users;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImp implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    // Create User
    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    // Get All Users
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Get User by ID
    public Users getUserById(Integer id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // Update User
    public Users updateUser(Integer id, Users userDetails) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setName(userDetails.getName());
        user.setUserName(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return usersRepository.save(user);
    }

    // Delete User
    public void deleteUser(Integer id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        usersRepository.delete(user);
    }
}
