/**
 * Main class for the BasicAuthApplication.
 */
package com.practice.basic_auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BasicAuthApplication {

  @Autowired
  private PasswordEncoder passwordEncoder;


  /**
   * The main entry point of the application.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {

    SpringApplication.run(BasicAuthApplication.class, args);

  }


}
