package com.example.demo.config.exception;

import com.example.demo.enums.MsgType;

public class ReadyEmailException extends BaseException {

  public ReadyEmailException() {
    super(MsgType.ReadyEmail);
  }
}
