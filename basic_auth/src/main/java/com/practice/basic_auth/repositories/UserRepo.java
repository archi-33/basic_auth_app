/**
 * Repository interface for managing User entities in the application.
 */
package com.practice.basic_auth.repositories;

import com.practice.basic_auth.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface extending JpaRepository to perform database operations on User entities.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

  /**
   * Retrieves a user by their email address.
   *
   * @param email The email address of the user to find.
   * @return An Optional containing the User entity if found, or an empty Optional if not found.
   */
  Optional<User> findByEmail(String email);

}
