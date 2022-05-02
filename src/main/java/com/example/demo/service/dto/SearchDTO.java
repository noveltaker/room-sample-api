package com.example.demo.service.dto;

import com.example.demo.enums.DealType;
import com.example.demo.enums.RoomType;
import com.example.demo.enums.SearchType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchDTO extends PageDTO {

  private SearchType type;

  private RoomType roomType;

  private DealType dealType;

  private Integer startDeposit;

  private Integer endDeposit;

  @Builder
  public SearchDTO(
      Integer page,
      Integer size,
      SearchType type,
      RoomType roomType,
      DealType dealType,
      Integer startDeposit,
      Integer endDeposit) {
    super(page, size);
    this.type = type;
    this.roomType = roomType;
    this.dealType = dealType;
    this.startDeposit = startDeposit;
    this.endDeposit = endDeposit;
  }
}
