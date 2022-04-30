package com.example.demo.domain;

import com.example.demo.config.exception.NotFoundException;
import com.example.demo.enums.RoomType;
import com.example.demo.utils.BaseEnumUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoomTest {

  @Test
  void base() {}

  @Test
  @DisplayName("RoomType foundOf Test Case")
  void foundOf() {

    String mockStr = "one";

    RoomType roomType = RoomType.ONE;

    RoomType value = (RoomType) BaseEnumUtil.foundOf(RoomType.class, mockStr);

    Assertions.assertEquals(roomType, value);
  }

  @Test
  @DisplayName("RoomType foundOf NotFoundException 예외 테스트")
  void foundOf_null() {

    String mockStr = null;

    Assertions.assertThrows(
        NotFoundException.class,
        () -> {
          BaseEnumUtil.foundOf(RoomType.class, mockStr);
        });
  }
}
