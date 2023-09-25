package com.practice.basic_auth.payloads;

import lombok.Data;

@Data
public class JwtRequest {
  private String email;
  private String password;

  ;
}
