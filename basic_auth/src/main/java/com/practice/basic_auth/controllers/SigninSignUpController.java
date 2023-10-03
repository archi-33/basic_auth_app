/**
 * Controller class for handling user sign-up and login operations.
 */
package com.practice.basic_auth.controllers;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.ApiResponse;
import com.practice.basic_auth.payloads.Error;
import com.practice.basic_auth.payloads.JwtRequest;
import com.practice.basic_auth.payloads.ServiceResponse;
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

  /**
   * Endpoint for creating a new user.
   *
   * @param user The User object to be created.
   * @return ResponseEntity containing ApiResponse with success message or error message.
   */
  @PostMapping("/signup")
  public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
    ServiceResponse<UserDto> createdUser = userService.createUser(user);
    if (createdUser.getSuccess()) {
      return new ResponseEntity<>((new ApiResponse("success", createdUser.getData(), null)),
          HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(
          (new ApiResponse("failure", null, new Error(createdUser.getMessage()))),
          HttpStatus.BAD_REQUEST);
    }

  }

  /**
   * Endpoint for generating an authentication token based on user credentials.
   *
   * @param jwtRequest The JwtRequest object containing user email and password.
   * @return ResponseEntity containing ApiResponse with success message and generated token or error message.
   */
  @PostMapping("/login")
  public ResponseEntity<ApiResponse> createToken(@RequestBody JwtRequest jwtRequest) {
    doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
    final UserDetails user = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
    String token = jwtHelper.generateToken(user);
//    ApiResponse response = new ApiResponse();
//    response.setData(token);
    ServiceResponse<UserDto> getUser = userService.getUser(jwtRequest.getEmail(),
        jwtRequest.getPassword());
    if (getUser.getSuccess()) {
      return new ResponseEntity<>((new ApiResponse("success", "token = " + token, null)),
          HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(
          (new ApiResponse("failure", null, new Error(getUser.getMessage()))),
          HttpStatus.BAD_REQUEST);
    }


  }

  /**
   * Perform user authentication.
   *
   * @param username User's email.
   * @param password User's password.
   */
  public void doAuthenticate(String username, String password) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        username, password);
    authenticationManager.authenticate(authenticationToken);
  }

  /**
   * Exception handler for BadCredentialsException.
   *
   * @return Error message for bad credentials.
   */
  @ExceptionHandler(BadCredentialsException.class)
  public String exceptionHandler() {
    return "Bad Credentials!!!!!!!!!!!!!!";
  }

}
