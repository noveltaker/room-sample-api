package com.example.demo.service.dto;

import com.example.demo.enums.DealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DealInfoDTO {
  private DealType type;

  private Integer monthlyAmount;

  private Integer deposit;
}
