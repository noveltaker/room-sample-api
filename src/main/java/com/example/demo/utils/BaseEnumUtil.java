package com.example.demo.utils;

import com.example.demo.config.exception.NotFoundException;
import com.example.demo.enums.BaseEnum;
import com.example.demo.enums.MsgType;

public final class BaseEnumUtil {

  private BaseEnumUtil() {}

  public static BaseEnum<?> foundOf(Class<? extends BaseEnum<?>> type, String str) {

    try {

      BaseEnum<?>[] enumConstants = (BaseEnum<?>[]) type.getEnumConstants();

      for (BaseEnum<?> baseEnum : enumConstants) {

        if (baseEnum.getValue().equals(str)) {
          return baseEnum;
        }
      }

    } catch (Exception e) {
      throw new NotFoundException(MsgType.BaseTypeError);
    }

    throw new NotFoundException(MsgType.NotFoundBaseType);
  }
}
