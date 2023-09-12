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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;


//  @Column(nullable = false)
  private String username;

//  @Column(nullable = false)
//  @Email
  private String email;
//  @Column(nullable = false, length = 5)
  private String password;

  private String firstName;
  private String lastName;
  private String gender;







}
