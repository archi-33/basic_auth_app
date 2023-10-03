/**
 * Payload class representing an error response in the application.
 */
package com.practice.basic_auth.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an error message in an API response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {

  /**
   * The error message describing the issue.
   */
  private String errorMessage;

}
