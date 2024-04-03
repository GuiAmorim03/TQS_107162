package tqs.ua.pt.backend.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import tqs.ua.pt.backend.models.Reservation;
import tqs.ua.pt.backend.models.Travel;

@DataJpaTest
@SuppressWarnings("deprecation")
public class ReservationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservationRepository reservationRepository;

    @MockBean
    private Travel travel;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        travel = new Travel("Lisbon", "Madrid", new Date(2024, 4, 3, 10, 30), 420, 30);
        entityManager.persistAndFlush(travel);

        reservation = new Reservation(travel, "Cliente Aleatório", "client@a.pt", "987654321", "123456789", "Rua dos Testes, Aveiro, nº3", "MB Way", "987654321", 3);
        entityManager.persistAndFlush(reservation);
    }

    @Test
    // find Reservation By Token
    void findByToken() {
        Reservation found = reservationRepository.findByToken(reservation.getToken());
        assertThat(found).isEqualTo(reservation);
    }

    @Test
    // return a Reservation that does not exist
    void findByTokenNotFound() {
        Reservation found = reservationRepository.findByToken(-99L);
        assertThat(found).isNull();
    }
}
