package com.example.demo.repository.support;

import com.example.demo.repository.support.search.SearchFactory;
import com.example.demo.service.dto.RoomInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomSupport {

  // 전체 목록 조회
  Page<RoomInfoDTO> findByAll(Pageable pageable, SearchFactory factory);
}
