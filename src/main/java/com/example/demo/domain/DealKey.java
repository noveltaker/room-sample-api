package com.example.demo.domain;

import com.example.demo.enums.DealType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"roomId", "type"})
public class DealKey implements Serializable {

  private Long roomId;

  private DealType type;
}
