package com.practice.basic_auth.controllers;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.ApiResponse;
import com.practice.basic_auth.payloads.LoginDto;
import com.practice.basic_auth.payloads.OutputResponse;
import com.practice.basic_auth.payloads.UserDto;
import com.practice.basic_auth.services.UserService;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/role")
@EnableMethodSecurity
public class UserController {
  @Autowired
  private UserService userService;


  @GetMapping("/access")
  @Secured("ROLE_ADMIN")
  @PreAuthorize("ROLE_ADMIN")
  public  String giveAccessToUser(@RequestParam String email, @RequestParam String userRole, Principal principal){
    return userService.giveAccess(email, userRole, principal);
  }

  @GetMapping("/fetchAllUsers")
  @PreAuthorize("ROLE_ADMIN")
  public List<UserDto> loadUsers(){
    return userService.loadAll();
  }


  @PutMapping("/update")
  @PreAuthorize("ROLE_USER")
  public ResponseEntity<ApiResponse> updateUserDetail(User user, Principal principal) {
    OutputResponse<UserDto> getUser =userService.update(user, principal);
    if(getUser.getSuccess()){
      return new ResponseEntity<>((new ApiResponse("success",getUser.getData(),null)), HttpStatus.OK);
    }else{
      return new ResponseEntity<>((new ApiResponse("failure",null, getUser.getMessage())), HttpStatus.BAD_REQUEST);
    }
  }







//  @PostMapping("/signin")
//  public ResponseEntity<ApiResponse> signin(@RequestBody LoginDto loginDto) {
//    OutputResponse<UserDto> user = userService.getUser(loginDto.getEmail(), loginDto.getPassword());
//    if(user.getSuccess()) {
//      return new ResponseEntity<>((new ApiResponse("success", user.getData(),null)), HttpStatus.OK);
//    }else
//      return new ResponseEntity<>((new ApiResponse("error",null, user.getMessage())), HttpStatus.BAD_REQUEST);
//  }

//  @GetMapping("/test")
//  public String sayHello(){
//    return "Saying hello after login.";
//  }

  @GetMapping("/getName")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  @Secured("ROLE_USER")
  public String giveName(Principal principal){
    return principal.getName();
  }
//
//  @PutMapping("/update")
//  public UserDto update(){
//  }

}




