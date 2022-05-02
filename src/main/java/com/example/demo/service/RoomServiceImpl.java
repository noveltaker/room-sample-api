package com.example.demo.service;

import com.example.demo.config.exception.NotFoundException;
import com.example.demo.domain.Deal;
import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.enums.MsgType;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.dto.PageDTO;
import com.example.demo.service.dto.RoomDTO;
import com.example.demo.service.dto.RoomInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

  private final RoomRepository roomRepository;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Room createRoom(RoomDTO dto, User user) {

    // room 데이터
    Room room = dto.toEntity(user);

    // 저장
    roomRepository.save(room);

    // 거래 유형 set 데이터
    Set<Deal> dealSet = dto.toDealSet(room.getId(), room);

    room.initDealTypes(dealSet);

    return room;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void removeOne(Long roomId) {
    roomRepository.deleteById(roomId);
  }

  @Override
  @Transactional(rollbackFor = Exception.class, readOnly = true)
  public Room getOne(Long id) {
    return roomRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(MsgType.NotFoundRoom));
  }

  @Override
  @Transactional(rollbackFor = Exception.class, readOnly = true)
  public Page<RoomInfo> getMyRoomList(Long userId, PageDTO dto) {
    return roomRepository.findByUser_Id(userId, dto.getPageRequest(), RoomInfo.class);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Room updateOne(Long id, RoomDTO dto) {

    Room entity =
        roomRepository.findById(id).orElseThrow(() -> new NotFoundException(MsgType.NotFoundRoom));

    entity.update(dto);

    return entity;
  }
}
