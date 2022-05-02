package com.example.demo.repository.support;

import com.example.demo.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomSupport {

    Page<Room> findByAll(Pageable pageable);
}
