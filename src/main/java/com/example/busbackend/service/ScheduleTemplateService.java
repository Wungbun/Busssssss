package com.example.busbackend.service;

import com.example.busbackend.entity.ScheduleTemplate;
import com.example.busbackend.mapper.ScheduleTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleTemplateService {

    @Autowired
    private ScheduleTemplateMapper scheduleTemplateMapper;

    public List<ScheduleTemplate> findByRouteId(Long routeId) {
        return scheduleTemplateMapper.findByRouteId(routeId);
    }

    public ScheduleTemplate save(ScheduleTemplate scheduleTemplate) {
        LocalDateTime now = LocalDateTime.now();
        if (scheduleTemplate.getId() == null) {
            scheduleTemplate.setCreatedAt(now);
            scheduleTemplate.setUpdatedAt(now);
            scheduleTemplateMapper.insert(scheduleTemplate);
        } else {
            scheduleTemplate.setUpdatedAt(now);
            scheduleTemplateMapper.update(scheduleTemplate);
        }
        return scheduleTemplate;
    }

    public void deleteById(Long id) {
        scheduleTemplateMapper.deleteById(id);
    }
}