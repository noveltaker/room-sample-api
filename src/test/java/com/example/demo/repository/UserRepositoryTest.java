package com.example.demo.repository;

import com.example.demo.config.QueryDslConfiguration;
import com.example.demo.domain.User;
import com.example.demo.mock.UserMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(QueryDslConfiguration.class)
class UserRepositoryTest {

  @Autowired private UserRepository userRepository;

  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Test
  @DisplayName("회원가입 로직")
  void save() {

    User mock = UserMock.getMock();

    mock.encodePassword(passwordEncoder.encode(mock.getPassword()));

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

  @Nested
  class Select {

    User mock = null;

    @BeforeEach
    void init() {

      User temp = UserMock.getMock();

      temp.encodePassword(passwordEncoder.encode(temp.getPassword()));

      mock = userRepository.save(temp);
    }

    @Test
    void findByEmail() {

      Optional<User> entityOptional = userRepository.findByEmail(mock.getEmail());

      Assertions.assertTrue(entityOptional.isPresent());

      User entity = entityOptional.get();

      org.assertj.core.api.Assertions.assertThat(mock).isEqualTo(entity);
      Assertions.assertEquals(entity.getId(), mock.getId());
      Assertions.assertEquals(entity.getEmail(), mock.getEmail());
      Assertions.assertEquals(entity.getPassword(), mock.getPassword());
    }
  }
}
