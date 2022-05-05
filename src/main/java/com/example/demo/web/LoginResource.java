package com.example.demo.web;

import com.example.demo.service.dto.LoginDTO;
import com.example.demo.web.docs.LoginResourceDocs;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginResource implements LoginResourceDocs {

  @Override
  @PostMapping("/login")
  public void login(@RequestBody LoginDTO dto) {}
}
