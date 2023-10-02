package com.practice.basic_auth.payloads;

import com.practice.basic_auth.entities.User;
import java.util.Collection;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto  {
  private Integer id;
  private String email;
  private String firstName;
  private String lastName;
  private String gender;
  private String role;


  public UserDto(User user){
    this.id = user.getId();
    this.email= user.getEmail();
    this.firstName= user.getFirstName();
    this.lastName= user.getLastName();
    this.gender= user.getGender();
    this.role = user.getRole();

  }

  public UserDto(UpdateUserDetailsDto user){
    this.email= user.getEmail();
    this.firstName= user.getFirstName();
    this.lastName= user.getLastName();
    this.gender= user.getGender();
  }

}
