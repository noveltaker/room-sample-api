package com.example.demo.config.security;

import com.example.demo.domain.User;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationSuccessHandler")
public class SuccessHandler implements AuthenticationSuccessHandler {

  @Value("${jwt.key}")
  private String key;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {

    DomainUser domainUser = (DomainUser) authentication.getPrincipal();

    User user = domainUser.getUser();

    Long id = user.getId();

    String email = domainUser.getUsername();

    String token = JwtUtil.createAccessToken(key, id, email);

    Message.Login loginMessage = Message.Login.builder().accessToken(token).build();

    MessageUtil.createMessage(HttpStatus.OK, response, loginMessage);
  }
}
