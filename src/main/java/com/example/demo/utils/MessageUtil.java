package com.example.demo.utils;

import com.example.demo.config.security.Message;
import com.example.demo.enums.MsgType;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class MessageUtil {

  private MessageUtil() {}

  public static void createErrorMessage(
      HttpStatus status, HttpServletResponse response, Exception ex, MsgType msgType)
      throws IOException {
    Message.Error error = setError(ex, msgType);
    createMessage(status, response, error);
  }

  public static Message.Error setError(Exception ex, MsgType msgType) {
    ex.printStackTrace();

    String detailMessage = ExceptionUtils.getStackTrace(ex);

    return Message.Error.builder()
        .code(msgType.getValue())
        .message(msgType.getMessage())
        .detail(detailMessage)
        .build();
  }

  public static void createMessage(HttpStatus status, HttpServletResponse response, Object message)
      throws IOException {
    response.reset();

    response.setStatus(status.value());

    response.setContentType("application/json");

    response.getWriter().write(JsonUtil.convertObjectToJson(message));
  }
}
