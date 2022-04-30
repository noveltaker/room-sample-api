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
}
