package com.example.demo.config.security;

import com.example.demo.domain.User;
import com.example.demo.utils.JwtUtil;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class JwtValidFilter extends OncePerRequestFilter implements Authority {

  private final Set<String> skipUrls = Set.of("/login", "/sign-up");

  private final AntPathMatcher pathMatcher = new AntPathMatcher();

  private final String jwtKey;

  public JwtValidFilter(String jwtKey) {
    this.jwtKey = jwtKey;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

    Claims claims = JwtUtil.parseJwtToken(authorization, jwtKey);

    Long id = Long.valueOf((Integer) claims.get("id"));

    String email = (String) claims.get("email");

    User loginUser = User.builder().id(id).email(email).build();

    DomainUser domainUser = new DomainUser(loginUser, getGrantedAuthorityList());

    UsernamePasswordAuthenticationToken authorityUser =
        new UsernamePasswordAuthenticationToken(
            domainUser, domainUser.getPassword(), domainUser.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authorityUser);

    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return skipUrls.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
  }
}
