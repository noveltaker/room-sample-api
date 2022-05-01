package com.example.demo.repository;

import com.example.demo.domain.Room;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  @Override
  @EntityGraph(attributePaths = {"dealSet"})
  Optional<Room> findById(Long aLong);
}
