package com.example.demo.config.security;

import com.example.demo.config.exception.TokenNotFoundException;
import com.example.demo.utils.JwtUtil;
import com.google.common.net.HttpHeaders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.ServletException;
import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
class JwtValidFilterTest {

  @Value("${jwt.key}")
  private String key;

  @Value("${user.id}")
  private String id;

  @Value("${user.email}")
  private String email;

  private JwtValidFilter filter;

  private String token;

  private MockHttpServletRequest req;
  private MockHttpServletResponse res;
  private MockFilterChain chain;

  @BeforeEach
  void init() {

    this.filter = new JwtValidFilter(key);
    this.token =
        SecurityConstants.TOKEN_PREFIX + JwtUtil.createAccessToken(key, Long.valueOf(id), email);
    this.req = new MockHttpServletRequest();
    this.res = new MockHttpServletResponse();
    this.chain = new MockFilterChain();
  }

  @Test
  @DisplayName("필터 기본 작동 동작 케이스")
  void validFilter() throws ServletException, IOException {
    req.addHeader(HttpHeaders.AUTHORIZATION, token);
    filter.doFilter(req, res, chain);
  }

  @Test
  @DisplayName("필터 토큰 없을 시 예외 처리")
  void validFilter_NotFoundTokenException() throws ServletException, IOException {
    Assertions.assertThrows(TokenNotFoundException.class, () -> filter.doFilter(req, res, chain));
  }

  @Test
  @DisplayName("not skip url 체크")
  void validFilter_not_skip() throws ServletException, IOException {
    req.setRequestURI("/api");
    Assertions.assertThrows(TokenNotFoundException.class, () -> filter.doFilter(req, res, chain));
  }

  @Test
  @DisplayName("login url 체크")
  void validFilter_Login_Path() throws ServletException, IOException {
    req.setRequestURI("/login");
    filter.doFilter(req, res, chain);
  }

  @Test
  @DisplayName("회원가입 url 체크")
  void validFilter_SignUp_Path() throws ServletException, IOException {
    req.setRequestURI("/sign-up");
    filter.doFilter(req, res, chain);
  }
}
