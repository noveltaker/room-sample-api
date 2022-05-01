package com.example.demo.service;

import com.example.demo.domain.Deal;
import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
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
    Set<Deal> dealSet = dto.toDealSet(room.getId());

    room.initDealTypes(dealSet);

    return room;
  }
}
