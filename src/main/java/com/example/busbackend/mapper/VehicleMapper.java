package com.example.busbackend.mapper;

import com.example.busbackend.entity.Vehicle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehicleMapper {
    Vehicle findById(Long id);
    List<Vehicle> findAll();
    int insert(Vehicle vehicle);
    int update(Vehicle vehicle);
    int deleteById(Long id);
}