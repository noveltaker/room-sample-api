package com.example.demo.mock;

import com.example.demo.domain.User;
import com.example.demo.service.dto.UserDTO;

public class UserMock {

  private static final Long id = 1L;
  private static final String email = "test@naver.com";

  private static final String password = "1234";

  public static User getMock() {
    return User.builder().id(id).email(email).password(password).build();
  }

  public static UserDTO getMockDTO() {
    return UserDTO.builder().email(email).password(password).build();
  }
}
