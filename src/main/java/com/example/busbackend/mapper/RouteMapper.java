package com.example.busbackend.mapper;

import com.example.busbackend.entity.Route;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RouteMapper {
    Route findById(Long id);
    List<Route> findAll();
    int insert(Route route);
    int update(Route route);
    int deleteById(Long id);
    List<Route> findByStartAndEnd(Long startCampusId, Long endCampusId); // 新增：根据校区ID查询线路
}