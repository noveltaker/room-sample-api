package com.example.demo.config.exception;

import com.example.demo.enums.MsgType;

public class TokenNotFoundException extends NotFoundException {

  public TokenNotFoundException() {
    super(MsgType.EMPTY_TOKEN_DOMAIN);
  }
}
