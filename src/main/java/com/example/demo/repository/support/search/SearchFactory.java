package com.example.demo.repository.support.search;

import com.example.demo.enums.SearchType;
import com.example.demo.service.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;

import java.util.Optional;

public final class SearchFactory implements SearchBuilder {

  private final SearchDTO dto;

  private SearchBuilder builder;

  public SearchFactory(SearchDTO dto) {
    this.dto = dto;
  }

  public SearchFactory init() {

    SearchType type = Optional.ofNullable(dto.getType()).orElse(SearchType.NONE);

    switch (type) {
      case ROOM:
        this.builder = new RoomTypeSearch(dto);
        break;
      case DEAL:
        this.builder = new DealTypeSearch(dto);
        break;
      case DEPOSIT:
        this.builder = new DepositSearch(dto);
        break;
      default:
        break;
    }

    return this;
  }

  @Override
  public BooleanBuilder getSearch() {
    if (builder == null) return new BooleanBuilder();
    return this.builder.getSearch();
  }
}
