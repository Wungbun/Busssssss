package com.example.busbackend.mapper;

import com.example.busbackend.entity.ScheduleTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleTemplateMapper {
    List<ScheduleTemplate> findByRouteId(Long routeId);
    int insert(ScheduleTemplate scheduleTemplate);
    int update(ScheduleTemplate scheduleTemplate);
    int deleteById(Long id);
}