/**
 * Payload class representing user details in the application.
 */
package com.practice.basic_auth.payloads;

import com.practice.basic_auth.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents user details including ID, email, first name, last name, gender, and role.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  /**
   * The unique identifier for the user.
   */
  private Integer id;

  /**
   * The email address of the user.
   */
  private String email;

  /**
   * The first name of the user.
   */
  private String firstName;

  /**
   * The last name of the user.
   */
  private String lastName;

  /**
   * The gender of the user.
   */
  private String gender;

  /**
   * The role(s) associated with the user.
   */
  private String role;

  /**
   * Constructs a UserDto object based on the provided User entity.
   *
   * @param user The User entity from which to create the user details.
   */
  public UserDto(User user) {

    this.id = user.getId();
    this.email = user.getEmail();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.gender = user.getGender();
    this.role = user.getRole();

  }

  /**
   * Constructs a UserDto object based on the provided UpdateUserDetailsDto.
   *
   * @param user The UpdateUserDetailsDto from which to create the user details.
   */
  public UserDto(UpdateUserDetailsDto user) {
    this.email = user.getEmail();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.gender = user.getGender();
  }

}
