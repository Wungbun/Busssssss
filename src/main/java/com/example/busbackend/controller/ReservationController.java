package com.example.busbackend.controller;

import com.example.busbackend.entity.Reservation;
import com.example.busbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAllReservations() {
        return reservationService.findAll();
    }

    @GetMapping
    public List<Reservation> getReservationsByUserId(@RequestParam Long userId) {
        return reservationService.findByUserId(userId);
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}