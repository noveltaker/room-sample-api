package com.example.demo.enums.converter;

import com.example.demo.config.exception.NotFoundException;
import com.example.demo.enums.DealType;
import com.example.demo.enums.MsgType;
import com.example.demo.utils.BaseEnumUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class DealTypeConverter implements AttributeConverter<DealType, String> {
  @Override
  public String convertToDatabaseColumn(DealType attribute) {
    return Optional.ofNullable(attribute)
        .orElseThrow(() -> new NotFoundException(MsgType.NotFoundDealType))
        .getValue();
  }

  @Override
  public DealType convertToEntityAttribute(String dbData) {
    return (DealType) BaseEnumUtil.foundOf(DealType.class, dbData);
  }
}
