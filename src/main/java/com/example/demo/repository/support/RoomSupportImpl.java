package com.example.demo.repository.support;

import com.example.demo.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class RoomSupportImpl extends QuerydslRepositorySupport implements RoomSupport {

  public RoomSupportImpl(Class<?> domainClass) {
    super(Room.class);
  }

  @Override
  public Page<Room> findByAll(Pageable pageable) {
    return null;
  }
}
