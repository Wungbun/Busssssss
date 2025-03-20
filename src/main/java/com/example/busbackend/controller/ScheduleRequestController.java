package com.example.busbackend.controller;

import com.example.busbackend.entity.ScheduleRequest;
import com.example.busbackend.service.ScheduleRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/schedule-requests")
public class ScheduleRequestController {
    @Autowired
    private ScheduleRequestService scheduleRequestService;

    @GetMapping
    public List<ScheduleRequest> getAllScheduleRequests() {
        return scheduleRequestService.findAll();
    }

    @GetMapping("/{id}")
    public ScheduleRequest getScheduleRequestById(@PathVariable Long id) {
        return scheduleRequestService.findById(id);
    }

    @PostMapping
    public void createScheduleRequest(@RequestBody ScheduleRequest request) {
        scheduleRequestService.save(request);
    }

    @PutMapping("/{id}")
    public void updateScheduleRequest(@PathVariable Long id, @RequestBody ScheduleRequest request) {
        request.setId(id);
        scheduleRequestService.save(request);
    }

    @DeleteMapping("/{id}")
    public void deleteScheduleRequest(@PathVariable Long id) {
        scheduleRequestService.delete(id);
    }
}