package com.example.demo.mock;

import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.enums.RoomType;
import com.example.demo.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMock {

  private static final Long id = 1L;

  private static final RoomType default_room_type = RoomType.ONE;

  private static final String name = "내방1";

  public static Room getMock(User user) {
    return Room.builder()
        .id(id)
        .type(default_room_type)
        .name(name)
        .user(user)
        .dealSet(DealMock.getMocks())
        .build();
  }

  public static Room getMockNotDeal(User user) {
    return Room.builder().id(id).type(default_room_type).name(name).user(user).build();
  }

  public static RoomDTO getRoomDTO() {
    return RoomDTO.builder()
        .name(name)
        .type(default_room_type)
        .dealSet(DealMock.createDealDtoSet())
        .build();
  }

  public static Page<RoomInfo> getRoomInfo(User user) {

    List<RoomInfo> list = List.of(convertInfo(getMock(user)));

    return new PageImpl<>(list, pageable(), 1);
  }

  public static Pageable pageable() {
    return PageRequest.of(0, 10);
  }

  public static RoomInfo convertInfo(Room room) {
    return new RoomTempInfo(room);
  }

  static class RoomTempInfo implements RoomInfo {

    private final Room room;

    RoomTempInfo(Room room) {
      this.room = room;
    }

    @Override
    public Long getId() {
      return room.getId();
    }

    @Override
    public String getName() {
      return room.getName();
    }

    @Override
    public RoomType getType() {
      return room.getType();
    }

    @Override
    public List<DealInfo> getDealSet() {
      return room.getDealSet().stream()
          .map(DealMock.DealTempInfo::new)
          .collect(Collectors.toList());
    }
  }

  public static PageDTO getPageDTO() {
    return new PageDTO(0, 10);
  }

  public static RoomDTO getUpdateDTO() {
    return RoomDTO.builder()
        .name("업데이트")
        .type(RoomType.THREE)
        .dealSet(DealMock.createDealDtoSingle())
        .build();
  }

  public static PageImpl<RoomInfoDTO> getPageMocks() {

    Room mock = getMock(UserMock.getMock());

    List<DealInfoDTO> mockDealList =
        mock.getDealSet().stream()
            .map(
                deal ->
                    new DealInfoDTO(
                        deal.getId().getType(), deal.getMonthlyAmount(), deal.getDeposit()))
            .collect(Collectors.toList());

    List<RoomInfoDTO> list =
        List.of(
            new RoomInfoDTO(
                mock.getId(),
                mock.getName(),
                mock.getType(),
                mock.getUser().getEmail(),
                mockDealList));

    Pageable pageable = pageable();

    return new PageImpl<>(list, pageable, list.size());
  }
}
