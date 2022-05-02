package com.example.demo.config.exception;

import com.example.demo.enums.MsgType;

public final class EmptyDealListException extends BaseException {
  public EmptyDealListException() {
    super(MsgType.EmptyDealSet);
  }
}
