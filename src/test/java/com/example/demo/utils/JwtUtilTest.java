package com.example.demo.utils;

import com.example.demo.config.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class JwtUtilTest {

  @Value("${jwt.key}")
  private String key;

  @Value("${user.id}")
  private String id;

  @Value("${user.email}")
  private String email;

  @Test
  @DisplayName("토큰 발금 및 파싱 테스트 케이스")
  void createTokenAndParseToken() {

    String mock =
        SecurityConstants.TOKEN_PREFIX + JwtUtil.createAccessToken(key, Long.valueOf(id), email);

    Claims claims = JwtUtil.parseJwtToken(mock, key);

    Long id = Long.valueOf((Integer) claims.get("id"));

    String email = (String) claims.get("email");

    Assertions.assertEquals(id, id);
    Assertions.assertEquals(email, email);
  }
}
