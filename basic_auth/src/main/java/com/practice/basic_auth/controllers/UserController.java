package com.practice.basic_auth.controllers;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.ApiResponse;
import com.practice.basic_auth.payloads.Error;
import com.practice.basic_auth.payloads.ServiceResponse;
import com.practice.basic_auth.payloads.UpdateRoleDto;
import com.practice.basic_auth.payloads.UpdateUserDetailsDto;
import com.practice.basic_auth.payloads.UserDto;
import com.practice.basic_auth.services.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@EnableMethodSecurity
public class UserController {
  @Autowired
  private UserService userService;


  @PutMapping("/access")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<ApiResponse> giveAccessToUser(@RequestBody UpdateRoleDto updateRoleDto, Principal principal){
    ServiceResponse<User> getUser =userService.giveAccess(updateRoleDto.getEmail(), updateRoleDto.getRole(), principal);
    if(getUser.getSuccess()){
      return new ResponseEntity<>((new ApiResponse("success",new UserDto(getUser.getData()),null)), HttpStatus.OK);
    }else{
      return new ResponseEntity<>((new ApiResponse("failure",null, new Error(getUser.getMessage()))), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/fetchAllUsers")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Secured("ROLE_ADMIN")
  public List<UserDto> loadUsers(){
    return userService.loadAll();
  }


  @PutMapping("/update")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<ApiResponse> updateUserDetail(@RequestBody UpdateUserDetailsDto updateUserDetailsDto, Principal principal) {
    ServiceResponse<UserDto> getUser =userService.update(updateUserDetailsDto, principal);
    if(getUser.getSuccess()){
      return new ResponseEntity<>((new ApiResponse("success",getUser.getData(),null)), HttpStatus.OK);
    }else{
      return new ResponseEntity<>((new ApiResponse("failure",null, new Error(getUser.getMessage()))), HttpStatus.BAD_REQUEST);
    }
  }

//  @PutMapping("/update")
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  public ResponseEntity<ApiResponse> updateAnyUserDetail(@RequestBody UpdateUserDetailsDto updateUserDetailsDto, Principal principal) {
//    ServiceResponse<UserDto> getUser =userService.update(updateUserDetailsDto, principal);
//    if(getUser.getSuccess()){
//      return new ResponseEntity<>((new ApiResponse("success",getUser.getData(),null)), HttpStatus.OK);
//    }else{
//      return new ResponseEntity<>((new ApiResponse("failure",null, new Error(getUser.getMessage()))), HttpStatus.BAD_REQUEST);
//    }
//  }

  @GetMapping("/getName")
  @PreAuthorize("hasRole('ROLE_USER')")
  public String giveName(Principal principal)
  {
    return principal.getName();
  }

//  @ExceptionHandler
//@ExceptionHandler(AuthenticationException.class)
//public Exception exceptionHandler() {
//  return new Exc
//}









//  @PostMapping("/signin")
//  public ResponseEntity<ApiResponse> signin(@RequestBody LoginDto loginDto) {
//    OutputResponse<UserDto> user = userService.getUser(loginDto.getEmail(), loginDto.getPassword());
//    if(user.getSuccess()) {
//      return new ResponseEntity<>((new ApiResponse("success", user.getData(),null)), HttpStatus.OK);
//    }else
//      return new ResponseEntity<>((new ApiResponse("error",null, user.getMessage())), HttpStatus.BAD_REQUEST);
//  }



}




