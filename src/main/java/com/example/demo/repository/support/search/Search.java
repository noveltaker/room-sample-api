package com.example.demo.repository.support.search;

import com.example.demo.service.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;

public abstract class Search {
  private final SearchDTO searchDTO;

  protected Search(SearchDTO searchDTO) {
    this.searchDTO = searchDTO;
  }

  public final SearchDTO getSearchDTO() {
    return searchDTO;
  }

  public abstract BooleanBuilder getSearch();
}
