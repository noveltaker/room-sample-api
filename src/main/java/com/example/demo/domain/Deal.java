package com.example.demo.domain;

import com.example.demo.enums.DealType;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.*;

@Getter
@Table
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"})
public class Deal {

  @EmbeddedId private DealKey id;

  // 월세
  @Column private Integer monthlyAmount;

  // 보증금
  @Column(nullable = false)
  private Integer deposit;

  @MapsId("roomId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Room room;

  @PrePersist
  void prePersist() {
    if (ObjectUtils.isEmpty(deposit)) this.deposit = 0;
    if (DealType.CHARTER_RENT.equals(id.getType())) this.monthlyAmount = null;
  }
}
