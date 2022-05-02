package com.example.demo.repository.support.search;

import com.example.demo.service.dto.SearchDTO;

public abstract class AbstractSearch implements SearchBuilder {
  private final SearchDTO searchDTO;

  protected AbstractSearch(SearchDTO searchDTO) {
    this.searchDTO = searchDTO;
  }

  public final SearchDTO getSearchDTO() {
    return searchDTO;
  }
}
