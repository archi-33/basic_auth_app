/**
 * Payload class representing a service response in the application.
 *
 * @param <T> The type of data associated with the response.
 */
package com.practice.basic_auth.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a response from a service operation, including success status, data, and an optional
 * message.
 *
 * @param <T> The type of data contained in the response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> {

  /**
   * Indicates whether the service operation was successful or not.
   */
  private Boolean success;

  /**
   * The data associated with the response (e.g., user details, tokens, etc.).
   */
  private T data;

  /**
   * An optional message providing additional information about the response, typically used in case
   * of an error.
   */
  private String message;

}


