package com.example.busbackend.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Campus {
    private Long id;
    private String name;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}