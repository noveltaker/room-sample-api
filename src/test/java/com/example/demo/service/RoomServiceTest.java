package com.example.demo.service;

import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.mock.RoomMock;
import com.example.demo.mock.UserMock;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.dto.RoomDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

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
  @DisplayName("room 데이터 저장")
  void createRoom() {

    User user = UserMock.getMock();

    Room mock = RoomMock.getMock(UserMock.getMock());

    BDDMockito.given(roomRepository.save(any())).willReturn(mock);

    RoomDTO roomDTO = RoomMock.getRoomDTO();

    Room entity = roomService.createRoom(roomDTO, user);

    BDDMockito.then(roomRepository).should().save(any());

    Assertions.assertEquals(mock.getName(), entity.getName());
    Assertions.assertEquals(mock.getType(), entity.getType());
    Assertions.assertEquals(mock.getUser(), entity.getUser());
  }

  @Test
  @DisplayName("room 하나 가지고 오기")
  void getOne() {

    Long roomId = 1L;

    Optional<Room> mockOptional = Optional.of(RoomMock.getMock(UserMock.getMock()));

    BDDMockito.given(roomRepository.findById(any())).willReturn(mockOptional);

    Room entity = roomService.getOne(roomId);

    Assertions.assertTrue(mockOptional.isPresent());

    BDDMockito.then(roomRepository).should().findById(any());

    Room mock = mockOptional.get();

    Assertions.assertEquals(mock.getId(), entity.getId());
    Assertions.assertEquals(mock.getName(), entity.getName());
    Assertions.assertEquals(mock.getType(), entity.getType());
    Assertions.assertEquals(mock.getUser(), entity.getUser());
    Assertions.assertEquals(mock.getDealSet(), entity.getDealSet());
  }
}
