package tqs.ua.pt.backend;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import tqs.ua.pt.backend.models.Reservation;
import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.repositories.ReservationRepository;
import tqs.ua.pt.backend.repositories.TravelRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("deprecation")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "application.properties")
public class IntegrationTest {
    
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private Travel travel;

    @BeforeEach
    public void setUp() {
        reservationRepository.deleteAll();
        travelRepository.deleteAll();
        travel = new Travel("Lisbon", "Porto", new Date(2024, 4, 5, 11, 30), 200, 10);
    }

    @Test
    void whenValidInput_thenCreateTravel() {
        restTemplate.postForEntity("/api/travel", travel, Travel.class);


        List<Travel> travels = travelRepository.findAll();
        assertThat(travels.size()).isEqualTo(1);
        assertThat(travels).extracting(Travel::getDeparture).containsOnly(travel.getDeparture());
        assertThat(travels).extracting(Travel::getArrival).containsOnly(travel.getArrival());
    }

    @Test
    void whenValidInput_thenCreateReservation() {
        travelRepository.save(travel); // é necessário guardar a viagem antes de criar a reserva
        Reservation reservation = new Reservation(travel, "Joao", "j@a.pt", "123456789", "123456789", "Rua", "MB", "123456789", 2);
     
        restTemplate.postForEntity("/api/reservation", reservation, Reservation.class);

        List<Reservation> reservations = reservationRepository.findAll();
        assertThat(reservations.size()).isEqualTo(1);
        assertThat(reservations).extracting(Reservation::getName).containsOnly(reservation.getName());
        assertThat(reservations).extracting(Reservation::getEmail).containsOnly(reservation.getEmail());   
    }

}
