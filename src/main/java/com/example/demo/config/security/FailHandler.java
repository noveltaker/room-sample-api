package com.example.demo.config.security;

import com.example.demo.enums.AuthenticationExceptionType;
import com.example.demo.enums.MsgType;
import com.example.demo.utils.MessageUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationFailureHandler")
public class FailHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {

    String exceptionMessage = getExceptionMessage(exception);

    Message.Error message =
        Message.Error.builder()
            .code(MsgType.AUTHENTICATION_ERROR.getValue())
            .message(exceptionMessage)
            .detail(ExceptionUtils.getStackTrace(exception))
            .build();

    MessageUtil.createMessage(HttpStatus.UNAUTHORIZED, response, message);
  }

  private String getExceptionMessage(AuthenticationException exception) {
    AuthenticationExceptionType authenticationTypes =
        AuthenticationExceptionType.findOf(exception.getClass().getSimpleName());
    return authenticationTypes.getValue();
  }
}
