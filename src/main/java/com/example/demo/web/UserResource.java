package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.web.docs.UserResourceDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserResource implements UserResourceDocs {

  private final UserService userService;

  @Override
  @PostMapping("/sign-up")
  public User signUp(@RequestBody UserDTO dto) {
    return userService.signUp(dto);
  }
}
