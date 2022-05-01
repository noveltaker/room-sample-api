package com.example.demo.service;

import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.service.dto.PageDTO;
import com.example.demo.service.dto.RoomDTO;
import com.example.demo.service.dto.RoomInfo;
import org.springframework.data.domain.Page;

public interface RoomService {

  // room 저장
  Room createRoom(RoomDTO dto, User user);

  // room 제거
  void removeOne(Long roomId);

  // room 하나 불러오기
  Room getOne(Long id);

  // 나의 room 데이터 리스트 업
  Page<RoomInfo> getMyRoomList(Long userId, PageDTO dto);
}
