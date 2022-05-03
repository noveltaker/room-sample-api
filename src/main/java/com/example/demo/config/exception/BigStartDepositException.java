package com.example.demo.config.exception;

import com.example.demo.enums.MsgType;

public class BigStartDepositException extends BaseException {

  public BigStartDepositException() {
    super(MsgType.BigStartDepositError);
  }
}
