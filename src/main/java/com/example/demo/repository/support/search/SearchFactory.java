package com.example.demo.repository.support.search;

import com.example.demo.enums.SearchType;
import com.example.demo.service.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;

import java.util.Optional;

public final class SearchFactory implements SearchBuilder {

  private final SearchDTO dto;

  public SearchFactory(SearchDTO dto) {
    this.dto = dto;
  }

  @Override
  public BooleanBuilder getSearch() {

    SearchType type = Optional.ofNullable(dto.getType()).orElse(SearchType.NONE);

    SearchBuilder searchBuilder = null;

    switch (type) {
      case ROOM:
        searchBuilder = new RoomTypeSearch(dto);
        break;
      case DEAL:
        searchBuilder = new DealTypeSearch(dto);
        break;
      case DEPOSIT:
        searchBuilder = new DepositSearch(dto);
        break;
      default:
        break;
    }

    return searchBuilder.getSearch();
  }
}
