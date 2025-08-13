package com.ecom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Users;
import com.ecom.repository.UsersRepository;
import com.ecom.service.AppUserDetailsService;
import com.ecom.util.JwtUtil;
import com.ecom.util.LoginRequest;
import com.ecom.util.TokenResponse;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authManager;
  private final AppUserDetailsService uds;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder encoder;
  private final UsersRepository usersRepo;

  public AuthController(AuthenticationManager authManager, AppUserDetailsService uds, JwtUtil jwtUtil,
		PasswordEncoder encoder, UsersRepository usersRepo) {
	super();
	this.authManager = authManager;
	this.uds = uds;
	this.jwtUtil = jwtUtil;
	this.encoder = encoder;
	this.usersRepo = usersRepo;
}

@PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest req) {
    Authentication auth = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
    UserDetails user = (UserDetails) auth.getPrincipal();
    String token = jwtUtil.generateToken(user);
    return ResponseEntity.ok(new TokenResponse(token));
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@Valid @RequestBody Users u) {
    u.setPassword(encoder.encode(u.getPassword()));
    if (u.getRole() == null || u.getRole().isBlank()) u.setRole("ROLE_USER");
    usersRepo.save(u);
    return ResponseEntity.ok("Registered");
  }
}
