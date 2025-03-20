package com.example.busbackend.mapper;

import com.example.busbackend.entity.ScheduleRequest;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ScheduleRequestMapper {
    List<ScheduleRequest> findAll();
    ScheduleRequest findById(Long id);
    void insert(ScheduleRequest request);
    void update(ScheduleRequest request);
    void delete(Long id);
}