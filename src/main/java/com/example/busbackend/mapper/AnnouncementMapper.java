package com.example.busbackend.mapper;

import com.example.busbackend.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    List<Announcement> findAll();
    List<Announcement> findByTargetRole(String targetRole); // 新增：按目标角色查询
    Announcement findById(Long id);
    int insert(Announcement announcement);
    int update(Announcement announcement);
    int deleteById(Long id);
}