package com.example.busbackend.service;

import com.example.busbackend.entity.Schedule;
import com.example.busbackend.entity.ScheduleTemplate;
import com.example.busbackend.mapper.ScheduleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private ScheduleTemplateService scheduleTemplateService;

    public List<Schedule> findAll() {
        logger.info("查询所有班次...");
        List<Schedule> schedules = scheduleMapper.findAll();
        logger.info("查询结果: {}", schedules);
        return schedules;
    }

    public Schedule findById(Long id) {
        logger.info("查询班次 ID: {}", id);
        return scheduleMapper.findById(id);
    }

    public List<Schedule> findByRouteId(Long routeId) {
        logger.info("查询线路 {} 的班次...", routeId);
        return scheduleMapper.findByRouteId(routeId);
    }

    public List<Schedule> findByDate(LocalDate date) {
        logger.info("查询日期 {} 的班次...", date);
        List<Schedule> schedules = scheduleMapper.findByDate(date);
        logger.info("查询结果: {}", schedules);
        return schedules;
    }

    public Schedule save(Schedule schedule) {
        LocalDateTime now = LocalDateTime.now();
        if (schedule.getId() == null) {
            schedule.setCreatedAt(now);
            schedule.setUpdatedAt(now);
            scheduleMapper.insert(schedule);
        } else {
            schedule.setUpdatedAt(now);
            scheduleMapper.update(schedule);
        }
        return schedule;
    }

    public void deleteById(Long id) {
        logger.info("删除班次 ID: {}", id);
        scheduleMapper.deleteById(id);
    }

    public List<Schedule> batchCreateSchedules(Long routeId, Long vehicleId, Long driverId, LocalDate date) {
        logger.info("为线路 {} 在日期 {} 批量创建班次，车辆 ID: {}, 司机 ID: {}", routeId, date, vehicleId, driverId);

        // 查询该线路的班次模板
        List<ScheduleTemplate> templates = scheduleTemplateService.findByRouteId(routeId);
        if (templates.isEmpty()) {
            throw new RuntimeException("该线路没有设置班次模板");
        }

        List<Schedule> schedules = new ArrayList<>();
        for (ScheduleTemplate template : templates) {
            // 将模板中的时间与目标日期组合
            LocalDateTime departureTime = LocalDateTime.of(date, template.getDepartureTime());
            // 确保生成的班次时间在当前时间之后
            if (departureTime.isBefore(LocalDateTime.now())) {
                continue; // 跳过过去的班次
            }

            Schedule schedule = new Schedule();
            schedule.setRouteId(routeId);
            schedule.setVehicleId(vehicleId);
            schedule.setDriverId(driverId);
            schedule.setDepartureTime(departureTime);
            schedule.setStatus("pending");
            schedule.setCreatedAt(LocalDateTime.now());
            schedule.setUpdatedAt(LocalDateTime.now());

            // 插入数据库
            save(schedule);
            schedules.add(schedule);
        }

        logger.info("成功创建 {} 个班次", schedules.size());
        return schedules;
    }

    public List<Schedule> findByDateAndRoute(LocalDate date, Long routeId) {
        logger.info("查询日期 {} 和线路 {} 的班次...", date, routeId);
        List<Schedule> schedules = scheduleMapper.findByDateAndRoute(date, routeId);
        logger.info("查询结果: {}", schedules);
        return schedules;
    }
}