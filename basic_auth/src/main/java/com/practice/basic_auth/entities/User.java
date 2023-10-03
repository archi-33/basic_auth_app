/**
 * Entity class representing a user in the application.
 */
package com.practice.basic_auth.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

  /**
   * Unique identifier for the user.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  /**
   * The email address of the user. Must be unique.
   */
  @Column(nullable = false, name = "Email")
  @Email
  private String email;

  /**
   * The password associated with the user's account.
   */
  @Column(nullable = false)
  private String password;

  /**
   * The first name of the user.
   */
  private String firstName;

  /**
   * The last name of the user.
   */

  private String lastName;

  /**
   * The gender of the user.
   */
  private String gender;

  //
//  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//  @JoinTable(name= "user_role",
//  joinColumns = {
//      @JoinColumn(name = "user_id")
//  },
//      inverseJoinColumns = {
//      @JoinColumn(name = "role_id")
//      }
//  )
  /**
   * The role(s) associated with the user.
   */
  private String role;

  /**
   * Indicates whether the user is active or not.
   */
  private boolean active;


}
