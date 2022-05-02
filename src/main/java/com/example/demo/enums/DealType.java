package com.example.demo.enums;

public enum DealType implements BaseEnum<String> {
  ALL("all"),
  MONTHLY_RENT("monthly"),
  CHARTER_RENT("charter");

  private final String value;

  DealType(String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return this.value;
  }
}
