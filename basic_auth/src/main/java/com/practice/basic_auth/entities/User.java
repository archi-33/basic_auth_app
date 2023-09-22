package com.practice.basic_auth.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


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
//  @JsonIgnore
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
