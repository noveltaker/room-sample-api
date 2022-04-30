package com.example.demo.utils;

import com.example.demo.config.exception.NotFoundException;
import com.example.demo.enums.BaseEnum;
import com.example.demo.enums.MsgType;

public class BaseEnumUtil {

  public static BaseEnum foundOf(Class<? extends BaseEnum> type, String str) {

    BaseEnum[] enumConstants = (BaseEnum[]) type.getEnumConstants();

    for (BaseEnum baseEnum : enumConstants) {

      if (baseEnum.getValue().equals(str)) {
        return baseEnum;
      }
    }

    throw new NotFoundException(MsgType.NotFoundBaseType);
  }
}
