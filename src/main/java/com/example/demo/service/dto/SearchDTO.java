package com.example.demo.service.dto;

import com.example.demo.enums.DealType;
import com.example.demo.enums.RoomType;
import com.example.demo.enums.SearchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchDTO {

  private SearchType type;

  private RoomType roomType;

  private DealType dealType;

  private Integer startDeposit;

  private Integer endDeposit;
}
