package com.example.busbackend.service;

import com.example.busbackend.entity.Vehicle;
import com.example.busbackend.mapper.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;

    public List<Vehicle> findAll() {
        return vehicleMapper.findAll();
    }

    public Vehicle findById(Long id) {
        return vehicleMapper.findById(id);
    }

    public Vehicle save(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            vehicleMapper.insert(vehicle);
        } else {
            vehicleMapper.update(vehicle);
        }
        return vehicle;
    }

    public void deleteById(Long id) {
        vehicleMapper.deleteById(id);
    }
}