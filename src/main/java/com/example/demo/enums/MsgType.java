package com.example.demo.enums;

public enum MsgType implements BaseEnum<String> {

  EmptyDealSet("E006" , "empty deal set"),
  NotFoundRoom("E005", "not found room data"),
  ReadyEmail("E004", "email is use "),
  NotFoundBaseType("E003", "not found base type"),
  NotFoundDealType("E002", "not found deal type"),
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
