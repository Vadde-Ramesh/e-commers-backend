package com.ecom.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.entity.Admin;
import com.ecom.entity.Users;
import com.ecom.repository.AdminRepository;
import com.ecom.repository.UsersRepository;


@Service
public class AppUserDetailsService implements UserDetailsService {
  private final AdminRepository adminRepo;
  private final UsersRepository usersRepo;
  public AppUserDetailsService(AdminRepository adminRepo, UsersRepository usersRepo) {
		super();
		this.adminRepo = adminRepo;
		this.usersRepo = usersRepo;
	}

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // accept either email or username
    return adminRepo.findByEmail(username).map(this::toPrincipal)
      .or(() -> adminRepo.findByUserName(username).map(this::toPrincipal))
      .or(() -> usersRepo.findByEmail(username).map(this::toPrincipal))
      .or(() -> usersRepo.findByUserName(username).map(this::toPrincipal))
      .orElseThrow(() -> new UsernameNotFoundException("User/Admin not found: " + username));
  }

  private UserDetails toPrincipal(Admin a) {
    return org.springframework.security.core.userdetails.User
      .withUsername(a.getEmail() != null ? a.getEmail() : a.getUsername())
      .password(a.getPassword())
      .authorities(a.getRole()) // e.g. ROLE_ADMIN
      .accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false)
      .build();
  }
  private UserDetails toPrincipal(Users u) {
    return org.springframework.security.core.userdetails.User
      .withUsername(u.getEmail() != null ? u.getEmail() : u.getUsername())
      .password(u.getPassword())
      .authorities(u.getRole()) // e.g. ROLE_USER
      .accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false)
      .build();
  }


}
