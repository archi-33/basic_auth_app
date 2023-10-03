/**
 * Payload class representing a JWT authentication response in the application.
 */
package com.practice.basic_auth.payloads;

import lombok.Data;

/**
 * Represents a JSON Web Token (JWT) authentication response containing the generated token.
 */
@Data
public class JwtResponse {

  /**
   * The JWT token generated upon successful authentication.
   */
  private String token;

}
