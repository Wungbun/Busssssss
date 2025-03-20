package com.example.busbackend.service;

import com.example.busbackend.entity.Route;
import com.example.busbackend.mapper.RouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RouteMapper routeMapper;

    public List<Route> findAll() {
        return routeMapper.findAll();
    }

    public Route findById(Long id) {
        return routeMapper.findById(id);
    }

    public Route save(Route route) {
        LocalDateTime now = LocalDateTime.now();
        if (route.getId() == null) {
            route.setCreatedAt(now);
            route.setUpdatedAt(now);
            routeMapper.insert(route);
        } else {
            route.setUpdatedAt(now);
            routeMapper.update(route);
        }
        return route;
    }

    public void deleteById(Long id) {
        routeMapper.deleteById(id);
    }

    public List<Route> findByStartAndEnd(Long startCampusId, Long endCampusId) {
        return routeMapper.findByStartAndEnd(startCampusId, endCampusId);
    }
}