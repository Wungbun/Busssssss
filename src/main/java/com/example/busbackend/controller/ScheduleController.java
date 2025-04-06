package com.example.busbackend.controller;

import com.example.busbackend.entity.Schedule;
import com.example.busbackend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.findAll();
    }

    @GetMapping("/{id}")
    public Schedule getScheduleById(@PathVariable Long id) {
        return scheduleService.findById(id);
    }

    @GetMapping("/by-route")
    public List<Schedule> getSchedulesByRouteId(@RequestParam Long routeId) {
        return scheduleService.findByRouteId(routeId);
    }

    @GetMapping("/by-date")
    public List<Schedule> getSchedulesByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return scheduleService.findByDate(date);
    }

    @PostMapping
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return scheduleService.save(schedule);
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        schedule.setId(id);
        return scheduleService.save(schedule);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteById(id);
    }

    @PostMapping("/batch-create")
    public List<Schedule> batchCreateSchedules(
            @RequestParam Long routeId,
            @RequestParam Long vehicleId,
            @RequestParam Long driverId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return scheduleService.batchCreateSchedules(routeId, vehicleId, driverId, date);
    }

    @GetMapping("/by-date-and-route")
    public List<Schedule> getSchedulesByDateAndRoute(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Long routeId) {
        return scheduleService.findByDateAndRoute(date, routeId);
    }
}