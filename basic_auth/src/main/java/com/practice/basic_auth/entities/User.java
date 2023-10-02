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


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false, name = "Email")
  @Email
  private String email;

  @Column(nullable = false)
  private String password;

  private String firstName;
  private String lastName;
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
  private String role;

 private boolean active;

}
