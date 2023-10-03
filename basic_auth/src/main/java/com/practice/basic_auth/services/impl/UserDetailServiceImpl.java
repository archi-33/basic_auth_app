/**
 * Service implementation for loading user details by username in the application.
 */
package com.practice.basic_auth.services.impl;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.CustomUserDetail;
import com.practice.basic_auth.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Service implementation for loading user details by username using Spring Security's
 * UserDetailsService.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

  /**
   * The repository for accessing User entities.
   */
  @Autowired
  private UserRepo userRepo;

  /**
   * Loads user details by their email (username).
   *
   * @param email The email (username) of the user to load.
   * @return A UserDetails object representing the loaded user.
   * @throws UsernameNotFoundException If the user is not found in the repository.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    //loading user by username(email)
    User user = userRepo.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found!!"));
    CustomUserDetail customUserDetail = new CustomUserDetail(user);
    return customUserDetail;
  }


}
