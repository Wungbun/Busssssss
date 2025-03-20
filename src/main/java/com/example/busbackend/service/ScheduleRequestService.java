package com.example.busbackend.service;

import com.example.busbackend.entity.ScheduleRequest;
import com.example.busbackend.mapper.ScheduleRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleRequestService {
    @Autowired
    private ScheduleRequestMapper scheduleRequestMapper;

    public List<ScheduleRequest> findAll() {
        return scheduleRequestMapper.findAll();
    }

    public ScheduleRequest findById(Long id) {
        return scheduleRequestMapper.findById(id);
    }

    public void save(ScheduleRequest request) {
        if (request.getId() == null) {
            request.setCreatedAt(LocalDateTime.now());
            scheduleRequestMapper.insert(request);
        } else {
            request.setUpdatedAt(LocalDateTime.now());
            scheduleRequestMapper.update(request);
        }
    }

    public void delete(Long id) {
        scheduleRequestMapper.delete(id);
    }
}