package com.example.demo.enums;

public enum MsgType implements BaseEnum<String> {
  AUTHENTICATION_ERROR("A001", "authentication error"),
  INTERNAL_SERVER_ERROR("S001", "internal server error"),
  EMPTY_TOKEN_DOMAIN("S002", "token empty"),
  NOT_MATCH_TOKEN("S003", "token is not match"),
  JWT_UNSUPPORTED("J001", "jwt not supported"),
  JWT_MALFORMED("J002", "jwt malformed"),
  JWT_EXPIRED("J003", "jwt expired"),
  JWT_SIGNATURE("J003", "jwt signature"),

  BigStartDepositError("E008", "big start deposit error"),
  BaseTypeError("E007", "base enum type error"),
  EmptyDealSet("E006", "empty deal set"),
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
