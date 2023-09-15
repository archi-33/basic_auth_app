package com.practice.basic_auth.services.impl;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.OutputResponse;
import com.practice.basic_auth.payloads.UserDto;
import com.practice.basic_auth.repositories.UserRepo;
import com.practice.basic_auth.services.UserService;
import org.hibernate.result.Output;
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
  public OutputResponse<UserDto> createUser(User user) {


    User existingUser = userRepo.findByEmail(user.getEmail());

    if(existingUser == null){
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      User savedUser = userRepo.save(user);
      return new OutputResponse<>(true, new UserDto(user), "Successfully created the user... PLEASE LOGIN!!!!!");
    }else{
      return new OutputResponse<>(false, null,"The specified Email id is already present");
    }
  }

  @Override
  public OutputResponse<UserDto> getUser(String email, String password) {

    User user=userRepo.findByEmail(email);

    if (user != null) {
      if (passwordEncoder.matches(password, user.getPassword())) {
        return new OutputResponse<>(true,  new UserDto(user), "User logged in successfully.");

      }
      else{
        return new OutputResponse<>(false, null, "Entered Password is wrong");
      }
    }else
      return new OutputResponse<>(false, null, "Entered Email ID does not exist");
  }

}
