package com.example.demo.repository.support.search;

import com.example.demo.enums.DealType;

public interface DealTypeMatcher {

  default boolean isNotAllType(DealType dealType) {
    return !DealType.ALL.equals(dealType);
  }
}
