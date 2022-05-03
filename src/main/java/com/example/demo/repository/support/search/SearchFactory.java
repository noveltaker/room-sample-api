package com.example.demo.repository.support.search;

import com.example.demo.enums.SearchType;
import com.example.demo.service.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Optional;

public final class SearchFactory {

  private final SearchDTO dto;

  private Search search;

  public SearchFactory(SearchDTO dto) {
    this.dto = dto;
  }

  public SearchFactory init() {

    SearchType type = Optional.ofNullable(dto.getType()).orElse(SearchType.NONE);

    switch (type) {
      case ROOM:
        this.search = new RoomTypeSearch(dto);
        break;
      case DEAL:
        this.search = new DealTypeSearch(dto);
        break;
      case DEPOSIT:
        this.search = new DepositSearch(dto);
        break;
      default:
        break;
    }

    return this;
  }

  public BooleanBuilder getSearch() {
    if (ObjectUtils.isEmpty(search)) return new BooleanBuilder();
    return search.getSearch();
  }
}
