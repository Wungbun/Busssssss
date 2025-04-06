package com.example.busbackend.controller;

import com.example.busbackend.entity.ScheduleTemplate;
import com.example.busbackend.service.ScheduleTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule-templates")
public class ScheduleTemplateController {

    @Autowired
    private ScheduleTemplateService scheduleTemplateService;

    @GetMapping
    public List<ScheduleTemplate> getTemplatesByRouteId(@RequestParam Long routeId) {
        return scheduleTemplateService.findByRouteId(routeId);
    }

    @PostMapping
    public ScheduleTemplate createTemplate(@RequestBody ScheduleTemplate scheduleTemplate) {
        return scheduleTemplateService.save(scheduleTemplate);
    }

    @PutMapping("/{id}")
    public ScheduleTemplate updateTemplate(@PathVariable Long id, @RequestBody ScheduleTemplate scheduleTemplate) {
        scheduleTemplate.setId(id);
        return scheduleTemplateService.save(scheduleTemplate);
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable Long id) {
        scheduleTemplateService.deleteById(id);
    }
}