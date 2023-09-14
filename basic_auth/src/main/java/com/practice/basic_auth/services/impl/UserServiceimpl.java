package com.practice.basic_auth.services.impl;

import com.practice.basic_auth.entities.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.practice.basic_auth.exceptions.UsernameNotFoundException;
import com.practice.basic_auth.payloads.ApiResponse;
import com.practice.basic_auth.repositories.UserRepo;
import com.practice.basic_auth.services.UserService;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {


  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserRepo userRepo;

  @Override
  public User createUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = userRepo.save(user);

    return savedUser;
  }

//  @Override
//  public UserDetails loadUserByUsername(String username) {
//    User user =userRepo.findByUsername(username);
//    if(user==null){
//      throw new UsernameNotFoundException("Invalid username");
//    }
//
//    return (UserDetails) user;
//  }

  @Override
  public User deleteUser(Integer id) {
    User user = userRepo.findById(id).orElseThrow(NoSuchElementException::new);
    this.userRepo.delete(user);
    return user;
  }
}
