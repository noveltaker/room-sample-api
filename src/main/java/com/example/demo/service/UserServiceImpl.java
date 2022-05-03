package com.example.demo.service;

import com.example.demo.config.exception.ReadyEmailException;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public User signUp(UserDTO dto) {

    if (userRepository.existsByEmail(dto.getEmail())) {
      // 중복 유저 처리
      throw new ReadyEmailException();
    }

    User entity = dto.toEntity();

    entity.encodePassword(passwordEncoder.encode(entity.getPassword()));

    userRepository.save(entity);

    return entity;
  }
}
