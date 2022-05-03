package com.example.demo.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public final class DomainUser extends User {

  private final com.example.demo.domain.User user;

  public DomainUser(com.example.demo.domain.User user, List<GrantedAuthority> authorities) {
    super(user.getEmail(), user.getPassword(), authorities);
    this.user = user;
  }

  public com.example.demo.domain.User getUser() {
    return user;
  }
}
