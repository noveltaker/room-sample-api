package com.example.demo.service;

import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.service.dto.RoomDTO;

public interface RoomService {

  // room 저장
  Room createRoom(RoomDTO dto, User user);
}
