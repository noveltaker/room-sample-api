package com.example.demo.service;

import com.example.demo.config.exception.ReadyEmailException;
import com.example.demo.domain.User;
import com.example.demo.mock.UserMock;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

  private UserService userService;

  @Mock private UserRepository userRepository;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    userService = new UserServiceImpl(userRepository, passwordEncoder);
  }

  @Test
  @DisplayName("회원 가입 로그인 로직 테스트 케이스")
  void signUp() {

    User mock = UserMock.getMock();

    Boolean isUse = false;

    BDDMockito.given(userRepository.existsByEmail(any())).willReturn(isUse);

    BDDMockito.given(userRepository.save(any())).willReturn(mock);

    UserDTO dto = UserMock.getMockDTO();

    User entity = userService.signUp(dto);

    BDDMockito.then(userRepository).should().existsByEmail(any());

    BDDMockito.then(userRepository).should().save(any());

    Assertions.assertEquals(mock.getEmail(), entity.getEmail());

    Assertions.assertTrue(passwordEncoder.matches(mock.getPassword(), entity.getPassword()));
  }

  @Test
  @DisplayName("회원 가입 로그인 로직 ReadyEmailException 테스트 케이스")
  void signUp_ReadyEmailException() {

    User mock = UserMock.getMock();

    Boolean isUse = true;

    BDDMockito.given(userRepository.existsByEmail(any())).willReturn(isUse);

    BDDMockito.given(userRepository.save(any())).willReturn(mock);

    UserDTO dto = UserMock.getMockDTO();

    Assertions.assertThrows(ReadyEmailException.class, () -> userService.signUp(dto));
  }
}
