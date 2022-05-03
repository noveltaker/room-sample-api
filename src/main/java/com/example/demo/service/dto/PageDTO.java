package com.example.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;

@Getter
@AllArgsConstructor
public class PageDTO {

  private Integer page;

  private Integer size;

  public PageRequest getPageRequest() {
    return PageRequest.of(getPage(), getSize());
  }

  public Integer getPage() {
    if (ObjectUtils.isEmpty(page)) return 0;
    return page;
  }

  public Integer getSize() {
    if (ObjectUtils.isEmpty(size)) return 10;
    return size;
  }
}
