package com.example.demo.repository.support.search;

import com.example.demo.enums.DealType;
import com.example.demo.service.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;

import static com.example.demo.domain.QDeal.deal;

public final class DealTypeSearch extends Search implements DealTypeMatcher {

  public DealTypeSearch(SearchDTO searchDTO) {
    super(searchDTO);
  }

  @Override
  public BooleanBuilder getSearch() {

    BooleanBuilder builder = new BooleanBuilder();

    SearchDTO dto = getSearchDTO();

    DealType dealType = dto.getDealType();

    if (isNotAllType(dealType)) {
      builder.and(deal.id.type.eq(dealType));
    }

    return builder;
  }
}
