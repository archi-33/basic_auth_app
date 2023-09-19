package com.practice.basic_auth.controllers;

import com.practice.basic_auth.payloads.JwtRequest;
import com.practice.basic_auth.payloads.JwtResponse;
import com.practice.basic_auth.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private JwtHelper jwtHelper;
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest jwtRequest){
    this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
    UserDetails user = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
    String token= jwtHelper.generateToken(user);
    JwtResponse response = new JwtResponse();
    response.setToken(token);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public void doAuthenticate(String username, String password){
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    authenticationManager.authenticate(authenticationToken);
  }

}
