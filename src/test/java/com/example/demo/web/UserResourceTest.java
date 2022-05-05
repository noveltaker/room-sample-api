package com.example.demo.web;

import com.example.demo.config.security.*;
import com.example.demo.domain.User;
import com.example.demo.mock.UserMock;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserResource.class)
@ActiveProfiles("test")
class UserResourceTest {

  private MockMvc mockMvc;

  @Value("${jwt.key}")
  private String key;

  @MockBean private UserService userService;

  @MockBean private SuccessHandler authenticationSuccessHandler;

  @MockBean private FailHandler authenticationFailureHandler;

  @MockBean private AuthenticationManager authenticationManager;

  @BeforeEach
  void init() {

    mockMvc =
        MockMvcBuilders.standaloneSetup(new UserResource(userService))
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .addFilters(new JwtExceptionFilter())
            .addFilters(new JwtValidFilter(key))
            .addFilters(
                new JwtLoginFilter(
                    "/login",
                    authenticationManager,
                    authenticationSuccessHandler,
                    authenticationFailureHandler))
            .build();
  }

  @Test
  @DisplayName("회원 가입 로직")
  void signUp() throws Exception {

    UserDTO dto = UserMock.getMockDTO();

    User mock = UserMock.getMock();

    BDDMockito.given(userService.signUp(any())).willReturn(mock);

    ResultActions action =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("/sign-up")
                    .content(JsonUtil.convertObjectToJson(dto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
            .andDo(print());

    BDDMockito.then(userService).should().signUp(any());

    action
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['id']").value(mock.getId()))
        .andExpect(jsonPath("$['email']").value(mock.getEmail()))
        .andExpect(jsonPath("$['password']").value(mock.getPassword()));
  }
}
