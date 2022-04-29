package com.example.demo.enums;

public enum MsgType implements BaseEnum<String> {
  NotFoundRoomType("E001", "not found room type");

  private final String code;

  private final String message;

  MsgType(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String getValue() {
    return this.code;
  }

  public String getMessage() {
    return message;
  }
}
