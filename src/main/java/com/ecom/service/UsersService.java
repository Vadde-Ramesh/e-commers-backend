package com.ecom.service;

import java.util.List;

import com.ecom.entity.Users;

import jakarta.validation.Valid;

public interface UsersService {

	Users createUser(@Valid Users user);

	List<Users> getAllUsers();

	Users getUserById(Integer id);

	Users updateUser(Integer id, @Valid Users userDetails);

	void deleteUser(Integer id);

}
