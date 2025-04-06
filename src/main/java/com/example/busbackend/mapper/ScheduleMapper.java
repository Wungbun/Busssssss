package com.example.busbackend.mapper;

import com.example.busbackend.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    Schedule findById(Long id);
    List<Schedule> findAll();
    int insert(Schedule schedule);
    int update(Schedule schedule);
    int deleteById(Long id);
    List<Schedule> findByRouteId(Long routeId);
    List<Schedule> findByDate(@Param("date") LocalDate date);
}