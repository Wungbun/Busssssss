package com.example.busbackend.service;

import com.example.busbackend.entity.Schedule;
import com.example.busbackend.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    public List<Schedule> findAll() {
        return scheduleMapper.findAll();
    }

    public Schedule findById(Long id) {
        return scheduleMapper.findById(id);
    }

    public Schedule save(Schedule schedule) {
        LocalDateTime now = LocalDateTime.now();
        if (schedule.getId() == null) {
            schedule.setCreatedAt(now);
            schedule.setUpdatedAt(now);
            scheduleMapper.insert(schedule);
        } else {
            schedule.setUpdatedAt(now);
            scheduleMapper.update(schedule);
        }
        return schedule;
    }

    public void deleteById(Long id) {
        scheduleMapper.deleteById(id);
    }

    public List<Schedule> findByRouteId(Long routeId) {
        return scheduleMapper.findByRouteId(routeId);
    }
}