package com.example.busbackend.service;

import com.example.busbackend.entity.Reservation;
import com.example.busbackend.entity.Schedule;
import com.example.busbackend.mapper.ReservationMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private ScheduleService scheduleService;

    public List<Reservation> findByScheduleId(Long scheduleId) {
        return reservationMapper.findByScheduleId(scheduleId);
    }

    public List<Reservation> findAll() {
        logger.info("查询所有预约记录...");
        List<Reservation> reservations = reservationMapper.findAll();
        logger.info("查询结果: {}", reservations);
        return reservations;
    }

    public List<Reservation> findByUserId(Long userId) {
        logger.info("查询用户 {} 的预约记录...", userId);
        List<Reservation> reservations = reservationMapper.findByUserId(userId);
        logger.info("查询结果: {}", reservations);
        return reservations;
    }

    public Reservation save(Reservation reservation) {
        LocalDateTime now = LocalDateTime.now();
        if (reservation.getId() == null) {
            reservation.setCreatedAt(now);
            reservation.setUpdatedAt(now);
            reservationMapper.insert(reservation);
        } else {
            reservation.setUpdatedAt(now);
            reservationMapper.update(reservation);
        }
        return reservation;
    }

    public void deleteById(Long id) {
        logger.info("删除预约记录 ID: {}", id);
        reservationMapper.deleteById(id);
    }

    @Transactional
    public Reservation createReservation(Long userId, Long scheduleId, Integer seatNumber) {
        // 检查座位是否已被占用
        List<Reservation> existingReservations = reservationMapper.findByScheduleId(scheduleId);
        boolean isOccupied = existingReservations.stream()
                .anyMatch(reservation -> reservation.getSeatNumber().equals(seatNumber));
        if (isOccupied) {
            throw new RuntimeException("座位已被占用");
        }

        // 检查班次状态
        Schedule schedule = scheduleService.findById(scheduleId);
        if (schedule == null || !schedule.getStatus().equals("pending")) {
            throw new RuntimeException("班次不可预约");
        }

        // 创建预约
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setScheduleId(scheduleId);
        reservation.setSeatNumber(seatNumber);
        reservation.setStatus("confirmed");
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());
        reservationMapper.insert(reservation);

        return reservation;
    }

    public Reservation findById(Long id) {
        return reservationMapper.findById(id);
    }
}