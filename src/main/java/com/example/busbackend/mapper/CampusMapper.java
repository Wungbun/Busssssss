package com.example.busbackend.mapper;

import com.example.busbackend.entity.Campus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CampusMapper {
    List<Campus> findAll();
    Campus findById(Long id);
}