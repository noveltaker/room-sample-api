package com.example.demo.repository.support.search;

import com.example.demo.service.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;

import static com.example.demo.domain.QDeal.deal;

public final class DepositSearch extends Search implements DepositSearchMatcher {

  public DepositSearch(SearchDTO searchDTO) {
    super(searchDTO);
  }

  @Override
  public BooleanBuilder getSearch() {

    BooleanBuilder builder = new BooleanBuilder();

    SearchDTO dto = getSearchDTO();

    isMatcher(dto.getStartDeposit(), dto.getEndDeposit());

    builder.and(deal.deposit.between(dto.getStartDeposit(), dto.getEndDeposit()));

    return builder;
  }
}
