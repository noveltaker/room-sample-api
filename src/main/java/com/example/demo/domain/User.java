package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Table
@Entity
@Builder
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(name = "hash_password", nullable = false)
  private String password;

  @Transient
  public void encodePassword(String password) {
    this.password = password;
  }
}
