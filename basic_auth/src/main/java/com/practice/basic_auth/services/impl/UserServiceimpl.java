package com.practice.basic_auth.services.impl;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.OutputResponse;
import com.practice.basic_auth.payloads.UserDto;
import com.practice.basic_auth.repositories.UserRepo;
import com.practice.basic_auth.services.UserService;
import java.util.Optional;
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


    Optional<User> existingUserOpt = userRepo.findByEmail(user.getEmail());

    if(existingUserOpt.isEmpty()){
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      User savedUser = userRepo.save(user);

      UserDto userDto = new UserDto();
      userDto.setEmail(savedUser.getEmail());
      userDto.setFirstName(savedUser.getFirstName());
      userDto.setLastName(savedUser.getLastName());
      userDto.setGender(savedUser.getGender());
      userDto.setId(savedUser.getId());
      return new OutputResponse<>(true, userDto, "Successfully created the user... PLEASE LOGIN!!!!!");
    }else{
      return new OutputResponse<>(false, null,"The specified Email id is already present");
    }
  }

  @Override
  public OutputResponse<UserDto> getUser(String email, String password) {

    Optional<User> userOpt=userRepo.findByEmail(email);

    if (userOpt.isPresent()) {
      User user = userOpt.get();
      if (passwordEncoder.matches(password, user.getPassword())) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setGender(user.getGender());
        userDto.setId(user.getId());
        return new OutputResponse<>(true,  userDto, "User logged in successfully.");

      }
      else{
        return new OutputResponse<>(false, null, "Entered Password is wrong");
      }
    }else
      return new OutputResponse<>(false, null, "Entered Email ID does not exist");
  }

}
