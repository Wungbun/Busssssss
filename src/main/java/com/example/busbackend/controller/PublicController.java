package com.example.busbackend.controller;

import com.example.busbackend.entity.*;
import com.example.busbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private UserService userService;

    @Autowired
    private CampusService campusService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/announcements")
    public List<Announcement> getPublicAnnouncements(@RequestParam String role) {
        return announcementService.findByTargetRole(role);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        User user = userService.findByUsername(username);
        Map<String, Object> result = new HashMap<>();

        if (user == null || !password.equals(user.getPassword())) {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
            return ResponseEntity.ok(result);
        }

        result.put("success", true);
        result.put("userId", user.getId());
        result.put("role", user.getRole());
        result.put("name", user.getName());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String name = request.get("name");
        String role = request.get("role");

        User existingUser = userService.findByUsername(username);
        Map<String, Object> result = new HashMap<>();

        if (existingUser != null) {
            result.put("success", false);
            result.put("message", "用户名已存在");
            return ResponseEntity.ok(result);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setRole(role != null ? role : "student");
        userService.save(user);

        result.put("success", true);
        result.put("userId", user.getId());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/campuses")
    public List<Campus> getAllCampuses() {
        return campusService.findAll();
    }

    @GetMapping("/routes")
    public List<Route> getRoutesByStartAndEnd(@RequestParam Long startCampusId, @RequestParam Long endCampusId) {
        return routeService.findByStartAndEnd(startCampusId, endCampusId);
    }

    @GetMapping("/schedules")
    public List<Schedule> getSchedulesByRouteId(@RequestParam Long routeId) {
        return scheduleService.findByRouteId(routeId);
    }

    @GetMapping("/seats")
    public Map<String, Object> getSeatsByScheduleId(@RequestParam Long scheduleId) {
        // 获取班次
        Schedule schedule = scheduleService.findById(scheduleId);
        if (schedule == null) {
            throw new RuntimeException("班次不存在");
        }

        // 获取车辆
        Vehicle vehicle = vehicleService.findById(schedule.getVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("车辆不存在");
        }

        // 获取已预约的座位
        List<Reservation> reservations = reservationService.findByScheduleId(scheduleId);
        List<Integer> occupiedSeats = reservations.stream()
                .map(Reservation::getSeatNumber)
                .collect(Collectors.toList());

        // 动态生成座位
        List<Map<String, Object>> seats = new ArrayList<>();
        for (int i = 1; i <= vehicle.getCapacity(); i++) {
            Map<String, Object> seat = new HashMap<>();
            seat.put("seatNumber", i);
            seat.put("isOccupied", occupiedSeats.contains(i));
            seats.add(seat);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("seats", seats);
        result.put("totalSeats", vehicle.getCapacity());
        result.put("availableSeats", vehicle.getCapacity() - occupiedSeats.size());
        return result;
    }

    @PostMapping("/reservations")
    public ResponseEntity<Map<String, Object>> createReservation(@RequestBody Map<String, Object> request) {
        Long userId = Long.valueOf(request.get("userId").toString());
        Long scheduleId = Long.valueOf(request.get("scheduleId").toString());
        Integer seatNumber = Integer.valueOf(request.get("seatNumber").toString());

        Map<String, Object> result = new HashMap<>();
        try {
            Reservation reservation = reservationService.createReservation(userId, scheduleId, seatNumber);
            result.put("success", true);
            result.put("reservationId", reservation.getId());
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
}