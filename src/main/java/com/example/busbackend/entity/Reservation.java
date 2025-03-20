package com.example.busbackend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reservation {
    private Long id;
    private Long userId;
    private Long scheduleId;
    private Integer seatNumber;
    private String status; // booked/checked_in/canceled
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}