package com.practice.basic_auth.payloads;

import com.practice.basic_auth.entities.User;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements UserDetails {
  private Integer id;
  private String email;
  private String firstName;
  private String lastName;
  private String gender;

  public UserDto(User user){
    this.id = user.getId();
    this.email= user.getEmail();
    this.firstName= user.getFirstName();
    this.lastName= user.getLastName();
    this.gender= user.getGender();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
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
}
