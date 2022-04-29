package com.example.demo.utils;

import com.example.demo.enums.BaseEnum;

public class BaseEnumUtil {

  public static BaseEnum foundOf(Class<? extends BaseEnum> type, String str) {

    BaseEnum[] enumConstants = (BaseEnum[]) type.getEnumConstants();

    for (BaseEnum baseEnum : enumConstants) {

      if (baseEnum.getValue().equals(str)) {
        return baseEnum;
      }
    }
    return null;
  }
}
