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
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements UserDetails {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;


  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  @Email
  private String email;

  @Column(nullable = false)
  private String password;

  private String firstName;
  private String lastName;
  private String gender;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "user_Role",
      joinColumns =@JoinColumn(name="user",referencedColumnName ="id" ),
      inverseJoinColumns = @JoinColumn(name="role", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String getUsername(){
    return this.email;
  }
}
