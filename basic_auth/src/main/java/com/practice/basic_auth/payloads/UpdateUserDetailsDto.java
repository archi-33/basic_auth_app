package com.practice.basic_auth.payloads;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDetailsDto {

  @Email
  private String email;

  private String password;

  private String firstName;
  private String lastName;
  private String gender;



}
