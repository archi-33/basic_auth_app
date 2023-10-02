package com.practice.basic_auth.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class ServiceResponse<T> {
    private Boolean success;
    private T data;
    private String message;
  }


