package com.example.demo.config.exception;

import com.example.demo.enums.MsgType;

public class NotFoundException extends BaseException {

  public NotFoundException(MsgType msgType) {
    super(msgType);
  }
}
