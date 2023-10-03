/**
 * Payload class representing a response in the application.
 */
package com.practice.basic_auth.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the response of an API operation, including the status, data, and error details.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {

  /**
   * The status of the API response (e.g., "success" or "failure").
   */
  private String status;

  /**
   * The data associated with the response (e.g., user details, tokens, etc.).
   */
  private Object data;

  /**
   * Error details in case of a failure response.
   */
  private Error error;

}

