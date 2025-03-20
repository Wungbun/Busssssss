package com.example.busbackend.service;

import com.example.busbackend.entity.Campus;
import com.example.busbackend.mapper.CampusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusService {
    @Autowired
    private CampusMapper campusMapper;

    public List<Campus> findAll() {
        return campusMapper.findAll();
    }

    public Campus findById(Long id) {
        return campusMapper.findById(id);
    }
}