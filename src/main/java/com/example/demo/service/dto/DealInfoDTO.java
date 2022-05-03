package com.example.demo.service.dto;

import com.example.demo.enums.DealType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DealInfoDTO {
  private DealType type;

  private Integer monthlyAmount;

  private Integer deposit;
}
