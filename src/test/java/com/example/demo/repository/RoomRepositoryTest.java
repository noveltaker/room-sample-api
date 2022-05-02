package com.example.demo.repository;

import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.mock.DealMock;
import com.example.demo.mock.RoomMock;
import com.example.demo.mock.UserMock;
import com.example.demo.service.dto.RoomDTO;
import com.example.demo.service.dto.RoomInfo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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

      Room mock = RoomMock.getMockNotDeal(user);

      Room entity = roomRepository.save(mock);

      entity.initDealTypes(DealMock.getMocks(entity.getId(), entity));

      mock.initDealTypes(DealMock.getMocks(entity.getId(), entity));

      roomRepository.flush();

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
  @DisplayName("조회")
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

      Assertions.assertEquals(mock.getId(), entity.getId());
      Assertions.assertEquals(mock.getName(), entity.getName());
      Assertions.assertEquals(mock.getType(), entity.getType());
      Assertions.assertEquals(mock.getUser(), entity.getUser());
      Assertions.assertEquals(mock.getDealSet(), entity.getDealSet());
    }

    @Test
    @DisplayName("나의 방 리스트 업")
    void findByUser_Id() {

      PageRequest page = PageRequest.of(0, 10);

      Page<RoomInfo> pageList = roomRepository.findByUser_Id(user.getId(), page, RoomInfo.class);

      List<RoomInfo> content = pageList.getContent();

      // 하나의 데이터 체크
      Assertions.assertEquals(content.size(), 1);

      RoomInfo entity = content.get(0);

      Assertions.assertEquals(mock.getId(), entity.getId());
      Assertions.assertEquals(mock.getName(), entity.getName());
      Assertions.assertEquals(mock.getType(), entity.getType());
      Assertions.assertEquals(mock.getDealSet().size(), entity.getDealSet().size());
    }
  }

  @Nested
  class Update {

    User user = null;

    Room mock = null;

    @BeforeEach
    void init() {
      user = userRepository.saveAndFlush(UserMock.getMock());

      mock = roomRepository.save(RoomMock.getMockNotDeal(user));

      roomRepository.flush();
    }

    @Test
    @DisplayName("업데이트 & update 메소드 테스트 케이스")
    void update() {

      RoomDTO mock1 = RoomMock.getUpdateDTO();

      Room update = roomRepository.findById(mock.getId()).orElseThrow();

      update.update(mock1);

      roomRepository.flush();

      Room entity = roomRepository.findById(mock.getId()).orElseThrow();

      Assertions.assertEquals(entity.getName(), mock1.getName());
      Assertions.assertEquals(entity.getType(), mock1.getType());
      Assertions.assertEquals(entity.getDealSet().size(), mock1.getDealSet().size());
    }
  }
}
