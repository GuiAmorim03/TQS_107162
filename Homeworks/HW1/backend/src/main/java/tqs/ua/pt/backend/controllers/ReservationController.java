package tqs.ua.pt.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.ua.pt.backend.models.Reservation;
import tqs.ua.pt.backend.services.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<Reservation>(reservationService.save(reservation), HttpStatus.CREATED);
    }

    @GetMapping("/reservation/{token}")
    public Reservation getReservationByToken(@PathVariable("token") Long token) {
        return reservationService.getReservationByToken(token);
    }
    
}
