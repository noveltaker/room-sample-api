package com.example.demo.mock;

import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.enums.RoomType;
import com.example.demo.service.dto.RoomDTO;

public class RoomMock {

  private static final Long id = 1L;

  private static final RoomType default_room_type = RoomType.ONE;

  private static final String name = "내방1";

  public static Room getMock(User user) {
    return Room.builder()
        .id(id)
        .type(default_room_type)
        .name(name)
        .user(user)
        .dealSet(DealMock.getMocks())
        .build();
  }

  public static RoomDTO getRoomDTO() {
    return RoomDTO.builder()
        .name(name)
        .type(default_room_type)
        .dealSet(DealMock.createDealDtoSet())
        .build();
  }
}
