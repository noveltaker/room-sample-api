package com.example.demo.config.security;

import com.example.demo.config.exception.NotFoundLoginUserException;
import com.example.demo.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public final class SecurityUtil {

  private SecurityUtil() {}

  private static Optional<User> getLoginUserOptional() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    DomainUser domainUser = (DomainUser) authentication.getPrincipal();

    User user = domainUser.getUser();

    return Optional.ofNullable(user);
  }

  public static Long getLoginUserId() {
    return getLoginUserOptional().orElseThrow(NotFoundLoginUserException::new).getId();
  }

  public static User getLoginUser() {
    return getLoginUserOptional().orElseThrow(NotFoundLoginUserException::new);
  }
}
