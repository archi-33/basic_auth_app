package com.practice.basic_auth.controllers;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.exceptions.UsernameNotFoundException;
import com.practice.basic_auth.payloads.ApiResponse;
import com.practice.basic_auth.services.UserService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;


  //create user
  @PostMapping("/")
  public ResponseEntity<User> createUser(@RequestBody User user){
    User createdUser= userService.createUser(user);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  //delete user
  @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
    User foundUser= userService.deleteUser(userId);

    return new ResponseEntity<>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);

  }

//  @GetMapping
//  ("/{username}")
//  public ResponseEntity<?> getSingleUser(@PathVariable String username)
//      throws UsernameNotFoundException {
//    return ResponseEntity.ok(this.userService.loadUserByUsername(username));
//  }


}
