package com.example.demo.mock;

import com.example.demo.domain.Deal;
import com.example.demo.enums.DealType;

import java.util.HashSet;
import java.util.Set;

public class DealMock {

  private static final Long default_room_id = 1L;

  public static Set<Deal> getMocks() {
    HashSet<Deal> set = new HashSet<>();

    set.add(
        Deal.initBuilder()
            .roomId(default_room_id)
            .type(DealType.MONTHLY_RENT)
            .monthlyAmount(50)
            .deposit(1000)
            .build());
    set.add(
        Deal.initBuilder()
            .roomId(default_room_id)
            .type(DealType.CHARTER_RENT)
            .deposit(5000)
            .build());
    return set;
  }
}
