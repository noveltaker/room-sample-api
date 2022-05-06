package com.example.demo.service.dto;

import com.example.demo.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomInfoDTO {

  private Long id;

  private String name;

  private RoomType type;

  private String userEmail;

  private List<DealInfoDTO> dealList;

  public List<DealInfoDTO> getDealList() {
    return dealList.isEmpty() ? Collections.emptyList() : dealList;
  }
}
