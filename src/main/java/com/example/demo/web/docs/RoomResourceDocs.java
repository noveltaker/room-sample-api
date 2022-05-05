package com.example.demo.web.docs;

import com.example.demo.domain.Room;
import com.example.demo.service.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;

public interface RoomResourceDocs {

  @Operation(summary = "내 방 만들기 API")
  Room createdRoom(RoomDTO dto);

  @Operation(summary = "방 하나 제거 API")
  void removeRoom(Long id);

  @Operation(summary = "내방 하나 불러오기 API")
  Room getRoom(Long id);

  @Operation(summary = "방 업데이트 API")
  Room updateRoom(Long id, RoomDTO dto);

  @Operation(summary = "내방 리스트 업 API")
  Page<RoomInfo> getMyRooms(PageDTO dto);

  @Operation(summary = "방 전체 목록 리스트업 API")
  Page<RoomInfoDTO> getAllRooms(SearchDTO dto);
}
