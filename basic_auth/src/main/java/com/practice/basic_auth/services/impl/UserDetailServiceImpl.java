package com.practice.basic_auth.services.impl;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.CustomUserDetail;
import com.practice.basic_auth.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    //loading user by username
    User user=userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!!"));
    CustomUserDetail customUserDetail =new CustomUserDetail(user);
    return customUserDetail;

  }


}
