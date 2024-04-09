package tqs.ua.pt.backend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.ua.pt.backend.models.Reservation;
import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.repositories.ReservationRepository;

@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock(lenient = true)
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation;
    private Travel travel;

    @BeforeEach
    public void setUp() {
        travel = new Travel("Lisbon", "Madrid", new Date(2024, 4, 7, 14, 30), 450, 13.5);
        reservation = new Reservation(travel, "Cliente Aleatório", "client@a.pt", "987654321", "123456789",
                "Rua dos Testes, Aveiro, nº3", "MB Way", "987654321", 3, "EUR");
    }

    @Test
    // create new reservation
    void testSaveReservation() {
        Mockito.when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation savedReservation = reservationService.save(reservation);
        assertThat(savedReservation.getTravel()).isEqualTo(travel);
        assertThat(savedReservation.getName()).isEqualTo("Cliente Aleatório");
        assertThat(savedReservation.getEmail()).isEqualTo("client@a.pt");
        assertThat(savedReservation.getPhone()).isEqualTo("987654321");
        assertThat(savedReservation.getNif()).isEqualTo("123456789");
        assertThat(savedReservation.getAddress()).isEqualTo("Rua dos Testes, Aveiro, nº3");
        assertThat(savedReservation.getPaymentMethod()).isEqualTo("MB Way");
        assertThat(savedReservation.getPaymentNumber()).isEqualTo("987654321");
        assertThat(savedReservation.getQtt()).isEqualTo(3);
    }

    @Test
    // test reservation with not enough seats
    void testSaveReservationNotEnoughSeats() {
        travel.setSeats(2);

        Mockito.when(reservationRepository.save(reservation)).thenReturn(reservation);

        assertThatThrownBy(() -> reservationService.save(reservation))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Not enough seats available");
    }

    @Test
    // find Reservation By Token
    void whenSearchReservationByToken_thenReturnReservation() {
        reservation.setToken(1L);
        Mockito.when(reservationRepository.findByToken(reservation.getToken())).thenReturn(reservation);

        Long token = 1L;
        Reservation found = reservationService.getReservationByToken(token);

        assertThat(found.getTravel()).isEqualTo(travel);
        assertThat(found.getName()).isEqualTo("Cliente Aleatório");
        assertThat(found.getEmail()).isEqualTo("client@a.pt");
        assertThat(found.getPhone()).isEqualTo("987654321");
        assertThat(found.getNif()).isEqualTo("123456789");
        assertThat(found.getAddress()).isEqualTo("Rua dos Testes, Aveiro, nº3");
        assertThat(found.getPaymentMethod()).isEqualTo("MB Way");
        assertThat(found.getPaymentNumber()).isEqualTo("987654321");
        assertThat(found.getQtt()).isEqualTo(3);
    }

    @Test
    // return all Reservations
    void whenSearchAllReservations_thenReturnAllReservations() {
        Mockito.when(reservationRepository.findAll()).thenReturn(java.util.List.of(reservation));

        List<Reservation> allReservations = reservationService.getAllReservations();
        assertThat(allReservations).hasSize(1).contains(reservation);
    }
}
