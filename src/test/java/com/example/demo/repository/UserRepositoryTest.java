package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.mock.UserMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

  @Autowired private UserRepository userRepository;

  @Test
  @DisplayName("회원가입 로직")
  void save() {

    User mock = UserMock.getMock();

    User entity = userRepository.save(mock);

    org.assertj.core.api.Assertions.assertThat(mock).isEqualTo(entity);
    Assertions.assertEquals(entity.getId(), mock.getId());
    Assertions.assertEquals(entity.getEmail(), mock.getEmail());
    Assertions.assertEquals(entity.getPassword(), mock.getPassword());
  }

  @Test
  @DisplayName("중복 유저가 있을때")
  void existsByEmail_ture() {

    User mock = userRepository.saveAndFlush(UserMock.getMock());

    boolean isUse = userRepository.existsByEmail(mock.getEmail());

    Assertions.assertTrue(isUse);
  }

  @Test
  @DisplayName("중복 유저가 없을 떄")
  void existsByEmail_false() {

    boolean isUse = userRepository.existsByEmail("test1");

    Assertions.assertFalse(isUse);
  }
}
