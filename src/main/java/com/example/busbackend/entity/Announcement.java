package com.example.busbackend.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Announcement {
    private Long id;
    private Long adminId;
    private String title;
    private String content;
    private String targetRole; // student/staff/driver/all
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}