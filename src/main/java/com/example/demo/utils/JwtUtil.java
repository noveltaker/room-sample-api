package com.example.demo.utils;

import com.example.demo.config.exception.TokenNotFoundException;
import com.example.demo.config.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.Date;

public class JwtUtil {

  public static String createAccessToken(String secretKey, Long id, String email) {
    return createToken(
        secretKey, id, email, new Date(new Date().getTime() + Duration.ofMinutes(30).toMillis()));
  }

  public static Claims parseJwtToken(String authorizationHeader, String jwtKey) {
    validationAuthorizationHeader(authorizationHeader);
    String passerHeader = extractToken(authorizationHeader);
    return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(passerHeader).getBody();
  }

  private static void validationAuthorizationHeader(String header) {
    if (StringUtils.isBlank(header) || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      throw new TokenNotFoundException();
    }
  }

  private static String extractToken(String header) {
    return header.substring(SecurityConstants.TOKEN_PREFIX.length());
  }

  private static String createToken(String secretKey, Long id, String email, Date expiration) {
    Date now = new Date();
    return Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setIssuer("fresh")
        .setIssuedAt(now)
        .setExpiration(expiration)
        .claim("id", id)
        .claim("email", email)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }
}
