package com.example.demo.service;

import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.mock.RoomMock;
import com.example.demo.mock.UserMock;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.dto.RoomDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RoomServiceTest {

  private RoomService roomService;

  @Mock private RoomRepository roomRepository;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    roomService = new RoomServiceImpl(roomRepository);
  }

  @Test
  void createRoom() {

    User user = UserMock.getMock();

    Room mock = RoomMock.getMock(UserMock.getMock());

    BDDMockito.given(roomRepository.save(any())).willReturn(mock);

    RoomDTO roomDTO = RoomMock.getRoomDTO();

    Room entity = roomService.createRoom(roomDTO, user);

    BDDMockito.then(roomRepository)
            .should()
            .save(any());

    Assertions.assertEquals(mock.getName() , entity.getName());
    Assertions.assertEquals(mock.getType() , entity.getType());
    Assertions.assertEquals(mock.getUser() , entity.getUser());
  }
}
