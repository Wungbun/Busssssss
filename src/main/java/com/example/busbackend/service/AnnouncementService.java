package com.example.busbackend.service;

import com.example.busbackend.entity.Announcement;
import com.example.busbackend.mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    public List<Announcement> findAll() {
        return announcementMapper.findAll();
    }

    public List<Announcement> findByTargetRole(String targetRole) {
        return announcementMapper.findByTargetRole(targetRole);
    }

    public Announcement findById(Long id) {
        return announcementMapper.findById(id);
    }

    public void save(Announcement announcement) {
        LocalDateTime now = LocalDateTime.now();
        if (announcement.getId() == null) {
            announcement.setCreatedAt(now);
            announcement.setUpdatedAt(now);
            announcementMapper.insert(announcement);
        } else {
            announcement.setUpdatedAt(now);
            announcementMapper.update(announcement);
        }
    }

    public void delete(Long id) {
        announcementMapper.deleteById(id);
    }
}