/**
 * Service interface for managing user-related operations.
 */
package com.practice.basic_auth.services;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.ServiceResponse;
import com.practice.basic_auth.payloads.UpdateUserDetailsDto;
import com.practice.basic_auth.payloads.UserDto;
import java.security.Principal;
import java.util.List;


public interface UserService {

  /**
   * Creates a new user.
   *
   * @param user The User object representing the user to be created.
   * @return A ServiceResponse containing the result of the user creation operation.
   */
  ServiceResponse<UserDto> createUser(User user);

  /**
   * Retrieves user details based on email and password.
   *
   * @param email    The email of the user.
   * @param password The password of the user.
   * @return A ServiceResponse containing user details and the result of the login operation.
   */
  ServiceResponse<UserDto> getUser(String email, String password);

  /**
   * Assigns a new role to a user.
   *
   * @param email     The email of the user to whom the role is assigned.
   * @param userRole  The new role to be assigned.
   * @param principal The Principal object representing the currently logged-in user.
   * @return A ServiceResponse indicating whether the role assignment was successful and providing
   * details.
   */
  ServiceResponse<User> giveAccess(String email, String userRole, Principal principal);

  /**
   * Loads all user details as UserDto objects.
   *
   * @return A list of UserDto objects containing user details.
   */
  List<UserDto> loadAll();

  /**
   * Updates user details.
   *
   * @param updateUserDetailsDto The updated user details.
   * @param principal            The Principal object representing the currently logged-in user.
   * @return A ServiceResponse indicating whether the update was successful and providing details.
   */
  ServiceResponse<UserDto> update(UpdateUserDetailsDto updateUserDetailsDto, Principal principal);

}
