package com.example.demo.service.dto;

import com.example.demo.enums.RoomType;

import java.util.List;

public interface RoomInfo {

  Long getId();

  String getName();

  RoomType getType();

  List<DealInfo> getDealSet();
}
