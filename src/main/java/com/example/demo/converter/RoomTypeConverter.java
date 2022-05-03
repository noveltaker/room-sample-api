package com.example.demo.converter;

import com.example.demo.config.exception.NotFoundException;
import com.example.demo.enums.MsgType;
import com.example.demo.enums.RoomType;
import com.example.demo.utils.BaseEnumUtil;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoomTypeConverter implements AttributeConverter<RoomType, String> {

  @Override
  public String convertToDatabaseColumn(RoomType attribute) {

    if (ObjectUtils.isEmpty(attribute) || RoomType.ALL.equals(attribute)){
      throw new NotFoundException(MsgType.NotFoundRoomType);
    }

    return attribute.getValue();
  }

  @Override
  public RoomType convertToEntityAttribute(String dbData) {
    return (RoomType) BaseEnumUtil.foundOf(RoomType.class, dbData);
  }
}
