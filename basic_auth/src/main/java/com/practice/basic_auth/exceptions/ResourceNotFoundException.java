package com.practice.basic_auth.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

  String resourceName;
  Integer fieldValue;


  public ResourceNotFoundException(String resourceName, Integer fieldValue) {
    super(String.format("%s not found with %s",resourceName, fieldValue));
    this.resourceName = resourceName;
    this.fieldValue = fieldValue;
}
}
