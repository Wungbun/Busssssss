package com.example.busbackend.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Vehicle {
    private Long id;
    private String plateNumber;
    private Integer capacity;
    private String status; // active/inactive
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}