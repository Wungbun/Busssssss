package com.example.busbackend.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScheduleRequest {
    private Long id;
    private Long userId;
    private Long routeId;
    private LocalDateTime requestedTime;
    private String reason;
    private String status; // pending/approved/rejected
    private Long reviewedBy;
    private LocalDateTime reviewedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}