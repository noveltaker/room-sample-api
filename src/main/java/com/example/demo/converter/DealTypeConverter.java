package com.example.demo.converter;

import com.example.demo.config.exception.NotFoundException;
import com.example.demo.enums.DealType;
import com.example.demo.enums.MsgType;
import com.example.demo.utils.BaseEnumUtil;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DealTypeConverter implements AttributeConverter<DealType, String> {
  @Override
  public String convertToDatabaseColumn(DealType attribute) {

    if (ObjectUtils.isEmpty(attribute) || DealType.ALL.equals(attribute)) {
      throw new NotFoundException(MsgType.NotFoundDealType);
    }

    return attribute.getValue();
  }

  @Override
  public DealType convertToEntityAttribute(String dbData) {
    return (DealType) BaseEnumUtil.foundOf(DealType.class, dbData);
  }
}
