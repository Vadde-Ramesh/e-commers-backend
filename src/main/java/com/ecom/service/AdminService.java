package com.ecom.service;

import java.util.List;

import com.ecom.entity.Admin;

import jakarta.validation.Valid;

public interface AdminService {

	Admin save(@Valid Admin admin);

	List<Admin> findAll();

	Admin findById(Integer id);

	void deleteById(Integer id);


}
