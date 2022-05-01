package com.example.demo.service.dto;

import com.example.demo.domain.Deal;
import com.example.demo.enums.DealType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DealDTO {

  private DealType type;

  private Integer monthlyAmount;

  private Integer deposit;

  public Deal toEntity(Long roomId) {
    return Deal.initBuilder()
        .roomId(roomId)
        .type(type)
        .monthlyAmount(this.monthlyAmount)
        .deposit(this.deposit)
        .build();
  }
}
