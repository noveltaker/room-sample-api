package com.example.demo.web;

import com.example.demo.config.security.SecurityUtil;
import com.example.demo.domain.Room;
import com.example.demo.service.RoomService;
import com.example.demo.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoomResource {

  private final RoomService roomService;

  @PostMapping("/room")
  public Room createdRoom(@RequestBody RoomDTO dto) {
    return roomService.createRoom(dto, SecurityUtil.getLoginUser());
  }

  @DeleteMapping("/room/{id}")
  public void removeRoom(@PathVariable Long id) {
    roomService.removeOne(id);
  }

  @GetMapping("/room/{id}")
  public Room getRoom(@PathVariable Long id) {
    return roomService.getOne(id);
  }

  @PutMapping("/room/{id}")
  public Room updateRoom(@PathVariable Long id, RoomDTO dto) {
    return roomService.updateOne(id, dto);
  }

  @GetMapping("/my-rooms")
  public Page<RoomInfo> getMyRooms(PageDTO dto) {
    return roomService.getMyRoomList(SecurityUtil.getLoginUserId(), dto);
  }

  @GetMapping("/rooms")
  public Page<RoomInfoDTO> getAllRooms(SearchDTO dto) {
    return roomService.getAllRoomList(dto);
  }
}
