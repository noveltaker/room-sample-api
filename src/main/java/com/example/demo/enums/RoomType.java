package com.example.demo.enums;

public enum RoomType implements BaseEnum<String> {
  ALL("all"),
  ONE("one"),
  TWE("twe"),
  THREE("three"),
  ;

  private final String value;

  RoomType(String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return this.value;
  }
}
