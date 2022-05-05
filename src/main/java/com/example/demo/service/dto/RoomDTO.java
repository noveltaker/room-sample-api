package com.example.demo.service.dto;

import com.example.demo.domain.Deal;
import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

  private String name;

  private RoomType type;

  @Builder.Default private Set<DealDTO> dealSet = new HashSet<>();

  public Room toEntity(User user) {
    return Room.initBuilder().name(this.name).type(this.type).user(user).build();
  }

  public Set<Deal> toDealSet(Long id, Room room) {
    return dealSet.stream()
        .map((dealDto) -> dealDto.toEntity(id, room))
        .collect(Collectors.toSet());
  }
}
