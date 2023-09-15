package com.practice.basic_auth.payloads;

import com.practice.basic_auth.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class OutputResponse<T> {
    private Boolean success;
    private T data;
    private String message;
  }


