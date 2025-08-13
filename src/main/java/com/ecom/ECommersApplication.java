package com.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecom.entity.Admin;
import com.ecom.repository.AdminRepository;

@SpringBootApplication
public class ECommersApplication implements CommandLineRunner{
	@Autowired
	AdminRepository adminRepo;

	public static void main(String[] args) {
		SpringApplication.run(ECommersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Admin admin = new Admin();
		admin.setEmail("ramesh@gmail.com");
		admin.setPassword("admin@123");
		admin.setName("admin");
		admin.setUsername("admin123");
		admin.setRole("admin");
		adminRepo.save(admin);
		
	}

}
