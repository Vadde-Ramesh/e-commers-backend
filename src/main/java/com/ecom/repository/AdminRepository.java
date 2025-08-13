package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	  Optional<Admin> findByEmail(String email);
	  Optional<Admin> findByUserName(String userName);

}
