package com.example.demo.repository.support.search;

import com.example.demo.enums.RoomType;

public interface RoomTypeMatcher {

  default boolean isNotAllType(RoomType roomType) {
    return !RoomType.ALL.equals(roomType);
  }
}
