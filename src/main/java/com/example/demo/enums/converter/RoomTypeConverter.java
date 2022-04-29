package com.example.demo.enums.converter;

import com.example.demo.config.exception.NotFoundException;
import com.example.demo.enums.MsgType;
import com.example.demo.enums.RoomType;
import com.example.demo.utils.BaseEnumUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class RoomTypeConverter implements AttributeConverter<RoomType, String> {

  @Override
  public String convertToDatabaseColumn(RoomType attribute) {
    return Optional.ofNullable(attribute)
        .orElseThrow(() -> new NotFoundException(MsgType.NotFoundRoomType))
        .getValue();
  }

  @Override
  public RoomType convertToEntityAttribute(String dbData) {
    return (RoomType) BaseEnumUtil.foundOf(RoomType.class, dbData);
  }
}
