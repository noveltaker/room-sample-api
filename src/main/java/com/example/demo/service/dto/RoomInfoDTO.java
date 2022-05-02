package com.example.demo.service.dto;

import com.example.demo.enums.RoomType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RoomInfoDTO {

  private Long id;

  private String name;

  private RoomType type;

  private String userEmail;

  private List<DealInfoDTO> dealList = new ArrayList<>();
}
