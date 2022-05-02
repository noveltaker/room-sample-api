package com.example.demo.repository.support.search;

import com.example.demo.enums.RoomType;
import com.example.demo.service.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;

import static com.example.demo.domain.QRoom.room;

public final class RoomTypeSearch extends AbstractSearch {

  RoomTypeSearch(SearchDTO dto) {
    super(dto);
  }

  @Override
  public BooleanBuilder getSearch() {

    SearchDTO dto = getSearchDTO();

    BooleanBuilder builder = new BooleanBuilder();

    RoomType roomType = dto.getRoomType();

    if (!RoomType.ALL.equals(roomType)) {
      builder.and(room.type.eq(roomType));
    }

    return builder;
  }
}
