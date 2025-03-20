package com.example.busbackend.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Route {
    private Long id;
    private Long startCampusId; // 起点校区ID
    private Long endCampusId;   // 终点校区ID
    private Double distance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}