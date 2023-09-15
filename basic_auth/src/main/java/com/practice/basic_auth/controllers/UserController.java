package com.practice.basic_auth.controllers;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.ApiResponse;
import com.practice.basic_auth.payloads.LoginDto;
import com.practice.basic_auth.payloads.OutputResponse;
import com.practice.basic_auth.payloads.UserDto;
import com.practice.basic_auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {


  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserService userService;


  //create user
  @PostMapping("/signup")
  public ResponseEntity<ApiResponse> createUser(@RequestBody User user){
    OutputResponse<UserDto> createdUser= userService.createUser(user);
    if(createdUser.getSuccess()){
      return new ResponseEntity<>((new ApiResponse("success", createdUser.getData(),null)), HttpStatus.CREATED);
    }else
      return new ResponseEntity<>((new ApiResponse("error",null, createdUser.getMessage())), HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/signin")
  public ResponseEntity<ApiResponse> signin(@RequestBody LoginDto loginDto) {
    OutputResponse<UserDto> user = userService.getUser(loginDto.getEmail(), loginDto.getPassword());
    if(user.getSuccess()) {
      return new ResponseEntity<>((new ApiResponse("success", user.getData(),null)), HttpStatus.OK);
    }else
      return new ResponseEntity<>((new ApiResponse("error",null, user.getMessage())), HttpStatus.BAD_REQUEST);
  }

}




