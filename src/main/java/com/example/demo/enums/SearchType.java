package com.example.demo.enums;

import com.example.demo.repository.support.search.DealTypeSearch;
import com.example.demo.repository.support.search.DepositSearch;
import com.example.demo.repository.support.search.RoomTypeSearch;
import com.example.demo.repository.support.search.Search;
import com.example.demo.service.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;

public enum SearchType {

  // 방유형
  ROOM {
    @Override
    protected Search getSearch(SearchDTO dto) {
      return new RoomTypeSearch(dto);
    }
  },
  // 거래 유형
  DEAL {
    @Override
    protected Search getSearch(SearchDTO dto) {
      return new DealTypeSearch(dto);
    }
  },
  // 가격 유형
  DEPOSIT {
    @Override
    protected Search getSearch(SearchDTO dto) {
      return new DepositSearch(dto);
    }
  };

  protected abstract Search getSearch(SearchDTO dto);

  public BooleanBuilder getSearchTypeBooleanBuilder(SearchDTO dto) {
    Search search = this.getSearch(dto);
    return search.getSearch();
  }
}
