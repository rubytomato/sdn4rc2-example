package com.example.sdn4rc2.converter;

import org.neo4j.ogm.typeconversion.AttributeConverter;

/**
 * Usage
 * 
 * @Convert(MoneyConverter.class)
 * private String money;
 *
 */
public class MoneyConverter implements AttributeConverter<String, Integer> {

  @Override
  public Integer toGraphProperty(String value) {
    if (value == null) {
      return Integer.valueOf("0");
    }
    return Integer.valueOf(value);
  }

  @Override
  public String toEntityAttribute(Integer value) {
    if (value == null) {
      return "";
    }
    return value.toString();
  }

}