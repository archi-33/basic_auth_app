package com.practice.basic_auth.controllers;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.ApiResponse;
import com.practice.basic_auth.payloads.JwtRequest;
import com.practice.basic_auth.payloads.OutputResponse;
import com.practice.basic_auth.payloads.UserDto;
import com.practice.basic_auth.security.JwtHelper;
import com.practice.basic_auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SigninSignUpController {

  @Autowired
  private JwtHelper jwtHelper;
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse> createUser(@RequestBody User user){
    OutputResponse<UserDto> createdUser= userService.createUser(user);
    if(createdUser.getSuccess()){
      return new ResponseEntity<>((new ApiResponse("success", createdUser.getData(),null)), HttpStatus.CREATED);
    }else
      return new ResponseEntity<>((new ApiResponse("failure",null, createdUser.getMessage())), HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse> createToken(@RequestBody JwtRequest jwtRequest){
    this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
    final UserDetails user = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
    String token= jwtHelper.generateToken(user);
    OutputResponse<UserDto> getUser= userService.getUser(jwtRequest.getEmail(),
        jwtRequest.getPassword());
    if(getUser.getSuccess()){
      return new ResponseEntity<>((new ApiResponse("success","token = "+token,null)), HttpStatus.CREATED);
    }else
      return new ResponseEntity<>((new ApiResponse("failure",null, getUser.getMessage())), HttpStatus.BAD_REQUEST);

  }

  public void doAuthenticate(String username, String password){
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    authenticationManager.authenticate(authenticationToken);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public String exceptionHandler(){
    return "Bad Credentials!!!!!!!!!!!!!!";
  }

}
