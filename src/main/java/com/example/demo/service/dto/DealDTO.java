package com.example.demo.service.dto;

import com.example.demo.domain.Deal;
import com.example.demo.domain.DealKey;
import com.example.demo.domain.Room;
import com.example.demo.enums.DealType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DealDTO {

  private DealType type;

  private Integer monthlyAmount;

  private Integer deposit;

  public Deal toEntity(Long roomId, Room room) {
    return Deal.builder()
        .id(new DealKey(roomId, type))
        .monthlyAmount(this.monthlyAmount)
        .deposit(this.deposit)
        .room(room)
        .build();
  }
}
