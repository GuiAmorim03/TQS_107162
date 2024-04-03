package tqs.ua.pt.backend.repositories;

import org.springframework.stereotype.Repository;

import tqs.ua.pt.backend.models.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public Reservation findByToken(Long token);    
}
