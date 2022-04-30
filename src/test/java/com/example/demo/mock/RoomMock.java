package com.example.demo.mock;

import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.enums.RoomType;

public class RoomMock {

  private static final Long id = 1L;

  private static final String name = "내방1";

  public static Room getMock(User user) {
    return Room.builder()
        .id(id)
        .type(RoomType.ONE)
        .name(name)
        .user(user)
        .dealSet(DealMock.getMocks())
        .build();
  }
}
