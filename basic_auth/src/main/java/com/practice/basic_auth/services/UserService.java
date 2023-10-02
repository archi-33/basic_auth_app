package com.practice.basic_auth.services;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.ServiceResponse;
import com.practice.basic_auth.payloads.UpdateUserDetailsDto;
import com.practice.basic_auth.payloads.UserDto;
import java.security.Principal;
import java.util.List;


public interface UserService{

  ServiceResponse<UserDto> createUser(User user);
  ServiceResponse<UserDto> getUser(String email, String password);
  ServiceResponse<User> giveAccess(String email, String userRole, Principal principal);

  List<UserDto> loadAll();
  ServiceResponse<UserDto> update(UpdateUserDetailsDto updateUserDetailsDto, Principal principal);

}
