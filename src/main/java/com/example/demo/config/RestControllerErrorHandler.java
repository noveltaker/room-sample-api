package com.example.demo.config;

import com.example.demo.config.exception.BaseException;
import com.example.demo.config.security.Message;
import com.example.demo.enums.MsgType;
import com.example.demo.utils.JsonUtil;
import com.example.demo.utils.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class RestControllerErrorHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public void handler(Exception ex, HttpServletResponse response) throws IOException {
    MsgType errorType = MsgType.INTERNAL_SERVER_ERROR;
    response.reset();
    Message.Error message = MessageUtil.setError(ex, errorType);
    response.getWriter().write(JsonUtil.convertObjectToJson(message));
  }

  @ExceptionHandler(BaseException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public void handler(BaseException ex, HttpServletResponse response) throws IOException {
    MsgType errorType = ex.getMsgType();
    response.reset();
    Message.Error message = MessageUtil.setError(ex, errorType);
    response.getWriter().write(JsonUtil.convertObjectToJson(message));
  }
}
