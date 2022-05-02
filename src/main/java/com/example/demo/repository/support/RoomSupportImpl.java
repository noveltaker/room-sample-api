package com.example.demo.repository.support;

import com.example.demo.domain.Room;
import com.example.demo.repository.support.search.Search;
import com.example.demo.repository.support.search.SearchFactory;
import com.example.demo.service.dto.DealInfoDTO;
import com.example.demo.service.dto.RoomInfoDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.domain.QDeal.deal;
import static com.example.demo.domain.QRoom.room;
import static com.example.demo.domain.QUser.user;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

public class RoomSupportImpl extends QuerydslRepositorySupport implements RoomSupport {

  private final JPAQueryFactory jpaQueryFactory;

  public RoomSupportImpl(JPAQueryFactory jpaQueryFactory) {
    super(Room.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public Page<RoomInfoDTO> findByAll(Pageable pageable, SearchFactory factory) {

    JPAQuery<Room> query =
        jpaQueryFactory
            .selectFrom(room)
            .innerJoin(room.user, user)
            .leftJoin(room.dealSet, deal)
            .where(factory.getSearch());

    List<RoomInfoDTO> result =
        query
            .transform(
                groupBy(room.id)
                    .list(
                        Projections.fields(
                            RoomInfoDTO.class,
                            room.id,
                            room.name,
                            room.type,
                            room.user.email.as("userEmail"),
                            list(Projections.fields(
                                    DealInfoDTO.class,
                                    deal.id.type.as("type"),
                                    deal.monthlyAmount,
                                    deal.deposit))
                                .as("dealList"))))
            .stream()
            .skip(pageable.getOffset())
            .limit(pageable.getPageSize())
            .collect(Collectors.toList());

    long total = query.fetchCount();

    return new PageImpl<>(result, pageable, total);
  }
}
