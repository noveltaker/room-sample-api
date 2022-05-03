package com.example.demo.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

interface Authority {

  default List<GrantedAuthority> getGrantedAuthorityList() {
    return List.of(new SimpleGrantedAuthority(SecurityConstants.ROLE_USER));
  }
}
