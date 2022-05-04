package com.example.demo.config.exception;

import com.example.demo.enums.MsgType;

public class NotFoundLoginUserException extends BaseException {

  public NotFoundLoginUserException() {
    super(MsgType.LoginUserNotFound);
  }
}
