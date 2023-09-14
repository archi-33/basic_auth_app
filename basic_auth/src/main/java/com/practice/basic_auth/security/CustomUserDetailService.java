package com.practice.basic_auth.security;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.exceptions.ResourceNotFoundException;
import com.practice.basic_auth.payloads.ApiResponse;
import com.practice.basic_auth.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //loading user by username
    User user=userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("Email",0));
    return user;
  }
}
