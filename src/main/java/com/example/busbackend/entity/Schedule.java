package com.example.busbackend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Schedule {
    private Long id;
    private Long routeId;
    private Long vehicleId;
    private Long driverId;
    private LocalDateTime departureTime;
    private String status; // pending, closed, running, completed
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}