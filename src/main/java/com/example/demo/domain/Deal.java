package com.example.demo.domain;

import com.example.demo.enums.DealType;
import lombok.*;

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

  @Column(nullable = false)
  private Integer monthlyAmount;

  @Column(nullable = false)
  private Integer deposit;

  @PrePersist
  void prePersist() {
    if (deposit == null) this.deposit = 0;
    if (DealType.CHARTER_RENT.equals(id.getType())) this.monthlyAmount = null;
  }

  @Builder(builderMethodName = "initBuilder")
  private Deal(Long roomId, DealType type, Integer monthlyAmount, Integer deposit) {
    this.id = new DealKey(roomId, type);
    this.monthlyAmount = monthlyAmount;
    this.deposit = deposit;
  }
}
