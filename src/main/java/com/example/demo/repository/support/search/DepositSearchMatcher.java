package com.example.demo.repository.support.search;

import com.example.demo.config.exception.BigStartDepositException;

public interface DepositSearchMatcher {

  default void isMatcher(Integer start, Integer end) {
    if (start > end) {
      throw new BigStartDepositException();
    }
  }
}
