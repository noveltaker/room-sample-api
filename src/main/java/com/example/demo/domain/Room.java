package com.example.demo.domain;

import com.example.demo.enums.RoomType;
import com.example.demo.service.dto.RoomDTO;
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

  @MapKey(name = "id")
  @Builder.Default
  @OneToMany(
      cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
      mappedBy = "room")
  private Set<Deal> dealSet = new HashSet<>();

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

  @Transient
  public void update(RoomDTO dto) {
    this.name = dto.getName();
    this.type = dto.getType();
    this.dealSet = dto.toDealSet(this.id, this);
  }
}
