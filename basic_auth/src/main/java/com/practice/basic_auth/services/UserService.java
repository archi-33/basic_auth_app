package com.practice.basic_auth.services;

import com.practice.basic_auth.entities.User;
import java.util.Optional;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService{
  User createUser(User user);
//  User getUserByUsername(String username) throws UsernameNotFoundException;
  User deleteUser(Integer id);
}
