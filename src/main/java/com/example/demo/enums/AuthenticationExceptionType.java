package com.example.demo.enums;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AuthenticationExceptionType implements BaseEnum<String> {
  BadCredentialsException("비밀번호 불일치", "비밀번호 불일치"),
  UsernameNotFoundException("계정없음", "계정없음"),
  AccountExpiredException("계정만료", "계정만료"),
  CredentialsExpiredException("비밀번호만료", "비밀번호만료"),
  DisabledException("계정비활성화", "계정비활성화"),
  LockedException("계정잠김", "계정잠김"),
  NoneException("알수없는 에러", "알 수 없는 에러 입니다.");

  private final String desc;

  private final String value;

  public String getDesc() {
    return desc;
  }

  @Override
  public String getValue() {
    return value;
  }

  AuthenticationExceptionType(String desc, String value) {
    this.desc = desc;
    this.value = value;
  }

  private static final Map<String, AuthenticationExceptionType> descriptions =
      Collections.unmodifiableMap(
          Stream.of(values())
              .collect(Collectors.toMap(AuthenticationExceptionType::name, Function.identity())));

  public static AuthenticationExceptionType findOf(String findValue) {
    return Optional.ofNullable(descriptions.get(findValue)).orElse(NoneException);
  }
}
