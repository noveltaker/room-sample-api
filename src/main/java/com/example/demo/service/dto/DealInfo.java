package com.example.demo.service.dto;

import com.example.demo.enums.DealType;
import org.springframework.beans.factory.annotation.Value;

public interface DealInfo {

  @Value("#{target.id.roomId}")
  Long getRoomId();

  @Value("#{target.id.type}")
  DealType getType();

  Integer getMonthlyAmount();

  Integer getDeposit();
}
