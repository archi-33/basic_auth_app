package com.practice.basic_auth.services;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.OutputResponse;
import com.practice.basic_auth.payloads.UserDto;
import java.util.Optional;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService{

  OutputResponse<UserDto> createUser(User user);
  OutputResponse<UserDto> getUser(String email, String password);
}
