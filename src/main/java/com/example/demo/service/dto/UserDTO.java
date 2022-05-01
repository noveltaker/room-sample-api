package com.example.demo.service.dto;

import com.example.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDTO {

  private String email;

  private String password;

  public User toEntity() {
    return User.builder().email(this.email).password(this.password).build();
  }
}
