/**
 * Payload class representing a request to update a user's role in the application.
 */
package com.practice.basic_auth.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to update a user's role with an email and the new role.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleDto {

  /**
   * The email address of the user whose role is to be updated.
   */
  private String email;

  /**
   * The new role to assign to the user.
   */
  private String role;
}
