package com.example.demo.domain;

import com.example.demo.config.exception.NotFoundException;
import com.example.demo.enums.RoomType;
import com.example.demo.mock.RoomMock;
import com.example.demo.mock.UserMock;
import com.example.demo.service.dto.RoomDTO;
import com.example.demo.utils.BaseEnumUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class RoomTest {

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

  @Test
  void initDealTypes() {
    Room room = RoomMock.getMockNotDeal(UserMock.getMock());

    room.initDealTypes(new HashSet<>());

    Assertions.assertEquals(room.getDealSet().size(), 0);
  }

  @Test
  void update() {

    Room room = RoomMock.getMock(UserMock.getMock());

    RoomDTO dto = RoomMock.getUpdateDTO();

    room.update(dto);

    Assertions.assertEquals(dto.getName(), room.getName());
    Assertions.assertEquals(dto.getType(), room.getType());

    dto.getDealSet()
        .forEach(
            val -> {
              Deal mock2 =
                  room.getDealSet().stream()
                      .filter(mock -> mock.getId().getType().equals(val.getType()))
                      .findFirst()
                      .orElseThrow();
              Assertions.assertEquals(val.getDeposit(), mock2.getDeposit());
              Assertions.assertEquals(val.getMonthlyAmount(), mock2.getMonthlyAmount());
            });
  }
}
