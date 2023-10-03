/**
 * Payload class representing a request to update user details in the application.
 */
package com.practice.basic_auth.payloads;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to update user details, including email, password, first name, last name,
 * and gender.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDetailsDto {

  /**
   * The new updated email address of the user.
   */
  @Email
  private String updatedMail;

  /**
   * The new password for the user (optional).
   */
  private String updatedPassword;

  /**
   * The updated first name of the user.
   */
  private String updatedFirstName;

  /**
   * The updated last name of the user.
   */
  private String updatedLastName;

  /**
   * The updated gender of the user.
   */
  private String updatedGender;


}
