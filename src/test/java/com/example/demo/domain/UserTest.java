package com.example.demo.domain;

import com.example.demo.mock.UserMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  void encodePassword() {

    User user = UserMock.getMock();

    String changePwd = "1234567";

    user.encodePassword(changePwd);

    Assertions.assertEquals(user.getPassword(), changePwd);
  }
}
