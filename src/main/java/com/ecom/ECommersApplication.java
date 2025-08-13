package com.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecom.entity.Admin;
import com.ecom.repository.AdminRepository;

@SpringBootApplication
public class ECommersApplication {
	@Autowired
	AdminRepository adminRepo;
	@Autowired
	PasswordEncoder password;

	public static void main(String[] args) {
		SpringApplication.run(ECommersApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Admin admin = new Admin();
//		admin.setEmail("admin@gmail.com");
//		admin.setName("admin");
//		admin.setPassword(password.encode("admin123"));
//		admin.setRole("ADMIN");
//		admin.setUserName("admin-ram");
//		adminRepo.save(admin);
//		
//	}

	

}
