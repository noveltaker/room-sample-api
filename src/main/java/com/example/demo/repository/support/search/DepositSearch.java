package com.example.demo.repository.support.search;

import com.example.demo.service.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;

import static com.example.demo.domain.QDeal.deal;

public final class DepositSearch extends AbstractSearch {

  DepositSearch(SearchDTO searchDTO) {
    super(searchDTO);
  }

  @Override
  public BooleanBuilder getSearch() {

    BooleanBuilder builder = new BooleanBuilder();

    SearchDTO dto = getSearchDTO();

    builder.and(deal.deposit.goe(dto.getStartDeposit()).and(deal.deposit.loe(dto.getEndDeposit())));

    return builder;
  }
}
