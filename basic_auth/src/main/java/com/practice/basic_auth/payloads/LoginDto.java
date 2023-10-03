/**
 * Payload class representing a login request in the application.
 */
package com.practice.basic_auth.payloads;

import lombok.Data;

/**
 * Represents a login request with an email and password for authentication.
 */
@Data
public class LoginDto {

  /**
   * The email address of the user.
   */
  private String email;

  /**
   * The password associated with the user's account.
   */
  private String password;

}
