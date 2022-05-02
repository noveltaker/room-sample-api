package com.example.demo.service.dto;

import com.example.demo.enums.DealType;
import lombok.Getter;

@Getter
public class DealInfoDTO {
  private DealType type;

  private Integer monthlyAmount;

  private Integer deposit;
}
