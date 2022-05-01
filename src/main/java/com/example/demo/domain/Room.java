package com.example.demo.domain;

import com.example.demo.enums.RoomType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Table
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"})
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private RoomType type;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Builder.Default @OneToMany private Set<Deal> dealSet = new HashSet<>();

  @Builder(builderMethodName = "initBuilder")
  private Room(String name, RoomType type, User user) {
    this.name = name;
    this.type = type;
    this.user = user;
  }

  @Transient
  public void initDealTypes(Set<Deal> dealSet) {
    this.dealSet = dealSet;
  }
}
