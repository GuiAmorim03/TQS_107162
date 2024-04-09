package tqs.ua.pt.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.ua.pt.backend.models.Reservation;
import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.repositories.ReservationRepository;

@Service
public class ReservationService {
    
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    
    public Reservation save(Reservation reservation) {
        Travel travelToBuy = reservation.getTravel();

        try {
            travelToBuy.addSeats(reservation.getQtt());
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Not enough seats available");
        }
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationByToken(Long token) {
        return reservationRepository.findByToken(token);
    }
}
