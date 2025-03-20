package com.example.busbackend.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String role; // student/staff/driver/admin
    private String name;
    private String phone;
    private String campus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}