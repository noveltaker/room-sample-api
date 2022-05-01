package com.example.demo.mock;

import com.example.demo.domain.Deal;
import com.example.demo.domain.DealKey;
import com.example.demo.enums.DealType;
import com.example.demo.service.dto.DealDTO;
import com.example.demo.service.dto.DealInfo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DealMock {

  private static final Long default_room_id = 1L;

  public static Set<Deal> getMocks() {

    HashSet<Deal> set = new HashSet<>();

    set.add(
        Deal.builder()
            .id(new DealKey(default_room_id, DealType.MONTHLY_RENT))
            .deposit(1000)
            .monthlyAmount(50)
            .build());

    set.add(
        Deal.builder()
            .id(new DealKey(default_room_id, DealType.CHARTER_RENT))
            .deposit(5000)
            .build());

    return set;
  }

  public static Set<DealDTO> createDealDtoSet() {
    return getMocks().stream()
        .map(
            value ->
                new DealDTO(value.getId().getType(), value.getMonthlyAmount(), value.getDeposit()))
        .collect(Collectors.toSet());
  }

  static class DealTempInfo implements DealInfo {

    private final Deal deal;

    public DealTempInfo(Deal deal) {
      this.deal = deal;
    }

    @Override
    public Long getRoomId() {
      return deal.getId().getRoomId();
    }

    @Override
    public DealType getType() {
      return deal.getId().getType();
    }

    @Override
    public Integer getMonthlyAmount() {
      return deal.getMonthlyAmount();
    }

    @Override
    public Integer getDeposit() {
      return deal.getDeposit();
    }
  }
}
