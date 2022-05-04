package com.example.demo.utils;

import com.example.demo.config.security.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonUtilTest {

  @Test
  @DisplayName("convert object to json format")
  void convertObjectToJson() {

    String mock = "{\"code\":\"001\",\"message\":\"test\",\"detail\":\"test\"}";

    Message.Error mockObj =
        Message.Error.builder().code("001").message("test").detail("test").build();

    String string = JsonUtil.convertObjectToJson(mockObj);

    Assertions.assertEquals(mock, string);
  }
}
