package com.ecom.util;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
  private String secret  = "VGhpc0lzQVRlc3QyNTZCaXQzMmNoYXJTZWNyZXRHdWFyZCEhISE="; // 256-bit base64 string
 
  private long expMins = 2* 60 * 1000;

  private Key key() { return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)); }

  public String generateToken(UserDetails user) {
    Instant now = Instant.now();
    return Jwts.builder()
      .setSubject(user.getUsername())
      .claim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
      .setIssuedAt(Date.from(now))
      .setExpiration(Date.from(now.plus(expMins, ChronoUnit.MINUTES)))
      .signWith(key(), SignatureAlgorithm.HS256)
      .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
      .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean isValid(String token, UserDetails user) {
    Claims c = Jwts.parserBuilder().setSigningKey(key()).build()
      .parseClaimsJws(token).getBody();
    return user.getUsername().equals(c.getSubject()) && c.getExpiration().after(new Date(System.currentTimeMillis()));
  }
}

