package com.example.demo.repository;

import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.mock.RoomMock;
import com.example.demo.mock.UserMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class RoomRepositoryTest {

  @Autowired private RoomRepository roomRepository;

  @Autowired private UserRepository userRepository;

  @Nested
  class Save {

    User user = null;

    @BeforeEach
    void init() {
      user = userRepository.saveAndFlush(UserMock.getMock());
    }

    @Test
    @DisplayName("내방 저장 로직")
    void save() {

      Room mock = RoomMock.getMock(user);

      Room entity = roomRepository.save(mock);

      org.assertj.core.api.Assertions.assertThat(mock).isEqualTo(entity);

      Assertions.assertEquals(mock.getId(), entity.getId());
      Assertions.assertEquals(mock.getName(), entity.getName());
      Assertions.assertEquals(mock.getType(), entity.getType());
      Assertions.assertEquals(mock.getUser(), entity.getUser());
      Assertions.assertEquals(mock.getDealSet(), entity.getDealSet());
    }
  }

  @Nested
  class Delete {

    User user = null;

    Room room = null;

    @BeforeEach
    void init() {
      user = userRepository.saveAndFlush(UserMock.getMock());

      room = roomRepository.save(RoomMock.getMockNotDeal(user));

      roomRepository.flush();
    }

    @Test
    void deleteId() {

      roomRepository.deleteById(room.getId());

      roomRepository.flush();

      Optional<Room> entity = roomRepository.findById(room.getId());

      Assertions.assertTrue(entity.isEmpty());
    }
  }

  @Nested
  class Select {

    User user = null;

    Room mock = null;

    @BeforeEach
    void init() {
      user = userRepository.saveAndFlush(UserMock.getMock());

      mock = roomRepository.save(RoomMock.getMockNotDeal(user));

      roomRepository.flush();
    }

    @Test
    @DisplayName("나의 방 하나 로직 테스트 케이스")
    void findById() {

      Optional<Room> entityOptional = roomRepository.findById(mock.getId());

      Assertions.assertTrue(entityOptional.isPresent());

      Room entity = entityOptional.get();

      Assertions.assertEquals(mock.getId() , entity.getId());
      Assertions.assertEquals(mock.getName() , entity.getName());
      Assertions.assertEquals(mock.getType() , entity.getType());
      Assertions.assertEquals(mock.getUser() , entity.getUser());
      Assertions.assertEquals(mock.getDealSet() , entity.getDealSet());
    }
  }
}
