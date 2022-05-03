package com.example.demo.service;

import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.enums.DealType;
import com.example.demo.enums.RoomType;
import com.example.demo.enums.SearchType;
import com.example.demo.mock.RoomMock;
import com.example.demo.mock.UserMock;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.dto.*;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

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
  @DisplayName("방 데이터 저장")
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
  @DisplayName("하나 방 가지고 오기")
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

  @Test
  @DisplayName("나의 방 리스트업")
  void getMyRoomList() {

    Page<RoomInfo> mocks = RoomMock.getRoomInfo(UserMock.getMock());

    BDDMockito.given(roomRepository.findByUser_Id(any(), any(), eq(RoomInfo.class)))
        .willReturn(mocks);

    PageDTO dto = RoomMock.getPageDTO();

    Page<RoomInfo> entities = roomService.getMyRoomList(1L, dto);

    List<RoomInfo> mockList = mocks.getContent();

    List<RoomInfo> entityList = entities.getContent();

    // size 체크
    Assertions.assertEquals(mockList.size(), entityList.size());

    RoomInfo mock = mockList.get(0);

    RoomInfo entity = entityList.get(0);

    Assertions.assertEquals(mock.getId(), entity.getId());
    Assertions.assertEquals(mock.getName(), entity.getName());
    Assertions.assertEquals(mock.getType(), entity.getType());
    Assertions.assertEquals(mock.getDealSet().size(), entity.getDealSet().size());
  }

  @Test
  @DisplayName("하나의 방 삭제")
  void removeOne() {

    Long id = 1L;

    Boolean isMock = Boolean.TRUE;

    BDDMockito.given(roomRepository.existsById(any())).willReturn(isMock);

    BDDMockito.willDoNothing().given(roomRepository).deleteById(any());

    roomService.removeOne(id);

    BDDMockito.then(roomRepository).should().existsById(any());

    BDDMockito.then(roomRepository).should().deleteById(any());
  }

  @Test
  @DisplayName("방 데이터 수정")
  void updateOne() {

    Long mockId = 1L;

    RoomDTO dto = RoomMock.getRoomDTO();

    Optional<Room> roomOptional = Optional.of(RoomMock.getMock(UserMock.getMock()));

    BDDMockito.given(roomRepository.findById(any())).willReturn(roomOptional);

    Room mock = roomOptional.get();

    Room entity = roomService.updateOne(mockId, dto);

    BDDMockito.then(roomRepository).should().findById(any());

    Assertions.assertEquals(mock.getId(), entity.getId());
    Assertions.assertEquals(mock.getName(), entity.getName());
    Assertions.assertEquals(mock.getType(), entity.getType());
    Assertions.assertEquals(mock.getDealSet().size(), entity.getDealSet().size());
  }

  @Test
  void getAllRoomList_RoomType() {

    PageImpl<RoomInfoDTO> pageMocks = RoomMock.getPageMocks();

    BDDMockito.given(roomRepository.findByAll(any(), any())).willReturn(pageMocks);

    SearchDTO dto = SearchDTO.builder().roomType(RoomType.ONE).type(SearchType.ROOM).build();

    Page<RoomInfoDTO> pages = roomService.getAllRoomList(dto);

    List<RoomInfoDTO> entities = pages.getContent();

    List<RoomInfoDTO> mocks = pageMocks.getContent();

    Assertions.assertEquals(mocks.size(), entities.size());

    RoomInfoDTO mock = mocks.get(0);

    RoomInfoDTO entity = entities.get(0);

    Assertions.assertEquals(mock.getId(), entity.getId());
    Assertions.assertEquals(mock.getName(), entity.getName());
    Assertions.assertEquals(mock.getType(), entity.getType());
    Assertions.assertEquals(mock.getDealList().size(), entity.getDealList().size());
  }

  @Test
  void getAllRoomList_DealType() {

    PageImpl<RoomInfoDTO> pageMocks = RoomMock.getPageMocks();

    BDDMockito.given(roomRepository.findByAll(any(), any())).willReturn(pageMocks);

    SearchDTO dto =
        SearchDTO.builder().dealType(DealType.CHARTER_RENT).type(SearchType.DEAL).build();

    Page<RoomInfoDTO> pages = roomService.getAllRoomList(dto);

    List<RoomInfoDTO> entities = pages.getContent();

    List<RoomInfoDTO> mocks = pageMocks.getContent();

    Assertions.assertEquals(mocks.size(), entities.size());

    RoomInfoDTO mock = mocks.get(0);

    RoomInfoDTO entity = entities.get(0);

    Assertions.assertEquals(mock.getId(), entity.getId());
    Assertions.assertEquals(mock.getName(), entity.getName());
    Assertions.assertEquals(mock.getType(), entity.getType());
    Assertions.assertEquals(mock.getDealList().size(), entity.getDealList().size());
  }

  @Test
  void getAllRoomList_Deposit() {

    PageImpl<RoomInfoDTO> pageMocks = RoomMock.getPageMocks();

    BDDMockito.given(roomRepository.findByAll(any(), any())).willReturn(pageMocks);

    SearchDTO dto =
            SearchDTO.builder().startDeposit(5000).endDeposit(5000).type(SearchType.DEPOSIT).build();

    Page<RoomInfoDTO> pages = roomService.getAllRoomList(dto);

    List<RoomInfoDTO> entities = pages.getContent();

    List<RoomInfoDTO> mocks = pageMocks.getContent();

    Assertions.assertEquals(mocks.size(), entities.size());

    RoomInfoDTO mock = mocks.get(0);

    RoomInfoDTO entity = entities.get(0);

    Assertions.assertEquals(mock.getId(), entity.getId());
    Assertions.assertEquals(mock.getName(), entity.getName());
    Assertions.assertEquals(mock.getType(), entity.getType());
    Assertions.assertEquals(mock.getDealList().size(), entity.getDealList().size());
  }
}
