package com.example.busbackend.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ScheduleTemplate {
    private Long id;
    private Long routeId;
    private LocalTime departureTime; // 仅存储时间，例如 08:00:00
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}