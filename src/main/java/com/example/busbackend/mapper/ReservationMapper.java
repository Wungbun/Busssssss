package com.example.busbackend.mapper;

import com.example.busbackend.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    Reservation findById(Long id);
    List<Reservation> findAll();
    List<Reservation> findByUserId(Long userId); // 新增
    int insert(Reservation reservation);
    int update(Reservation reservation);
    int deleteById(Long id); // 新增
    List<Reservation> findByScheduleId(Long scheduleId);
}