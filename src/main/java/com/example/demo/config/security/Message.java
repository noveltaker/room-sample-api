package com.example.demo.config.security;

import lombok.*;

public class Message {

  @Getter
  @Builder
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Login {
    private String accessToken;
  }

  @Getter
  @Builder
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Error {
    private String code;
    private String message;
    private String detail;
  }
}
