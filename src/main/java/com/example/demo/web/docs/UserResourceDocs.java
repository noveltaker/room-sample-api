package com.example.demo.web.docs;

import com.example.demo.domain.User;
import com.example.demo.service.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;

public interface UserResourceDocs {

  @Operation(summary = "회원가입 API")
  User signUp(UserDTO dto);
}
