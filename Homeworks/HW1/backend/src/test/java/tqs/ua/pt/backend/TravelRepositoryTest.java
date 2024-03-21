package tqs.ua.pt.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.repositories.TravelRepository;

@DataJpaTest
@SuppressWarnings("deprecation")
public class TravelRepositoryTest {
    // Unit Tests

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TravelRepository travelRepository;

    @Test
    // find Travel By Id
    void findByTravelId() {
        Travel travel = new Travel("Lisbon", "Porto", new Date(2024, 04, 05, 14, 30), 11.5);
        entityManager.persistAndFlush(travel);

        Travel found = travelRepository.findByTravelId(travel.getTravelId());
        assertThat(found).isEqualTo(travel);
    }

    @Test
    // find Travels By Departure
    void findByDeparture() {
        Travel travel1 = new Travel("Lisbon", "Porto", new Date(2024, 04, 07, 12, 30), 11.5);
        Travel travel2 = new Travel("Lisbon", "Madrid", new Date(2024, 04, 06, 14, 30), 15);
        Travel travel3 = new Travel("Lisbon", "Paris", new Date(2024, 04, 07, 11, 30), 25);
        Travel travel4 = new Travel("Porto", "Lisbon", new Date(2024, 04, 07, 16, 30), 11.5);
        Travel travel5 = new Travel("Lisbon", "Sevilla", new Date(2024, 04, 06, 14, 30), 12.5);
        entityManager.persistAndFlush(travel1);
        entityManager.persistAndFlush(travel2);
        entityManager.persistAndFlush(travel3);
        entityManager.persistAndFlush(travel4);
        entityManager.persistAndFlush(travel5);

        List<Travel> travelsFound = travelRepository.findByDeparture("Lisbon");

        assertThat(travelsFound).hasSize(4);
        assertThat(travelsFound.get(0)).isEqualTo(travel1);
        assertThat(travelsFound.get(1)).isEqualTo(travel2);
        assertThat(travelsFound.get(2)).isEqualTo(travel3);
        assertThat(travelsFound.get(3)).isEqualTo(travel5);
    }

    @Test
    // find Travels By Arrival
    void findByArrival() {
        Travel travel1 = new Travel("Lisbon", "Madrid", new Date(2024, 04, 06, 14, 30), 15);
        Travel travel2 = new Travel("Barcelona", "Madrid", new Date(2024, 04, 06, 14, 30), 15);
        Travel travel3 = new Travel("Madrid", "Sevilla", new Date(2024, 04, 06, 14, 30), 15);
        Travel travel4 = new Travel("Bilbao", "Madrid", new Date(2024, 04, 06, 14, 30), 15);

        entityManager.persistAndFlush(travel1);
        entityManager.persistAndFlush(travel2);
        entityManager.persistAndFlush(travel3);
        entityManager.persistAndFlush(travel4);

        List<Travel> travelsFound = travelRepository.findByArrival("Madrid");

        assertThat(travelsFound).hasSize(3);
        assertThat(travelsFound.get(0)).isEqualTo(travel1);
        assertThat(travelsFound.get(1)).isEqualTo(travel2);
        assertThat(travelsFound.get(2)).isEqualTo(travel4);

    }

    @Test
    // find Travels By Departure and Arrival
    void findByDepartureAndArrival() {
        Travel travel1 = new Travel("Lisbon", "Madrid", new Date(2024, 04, 06, 14, 30), 15);
        Travel travel2 = new Travel("Lisbon", "Madrid", new Date(2024, 04, 07, 14, 30), 15);
        Travel travel3 = new Travel("Madrid", "Sevilla", new Date(2024, 04, 06, 14, 30), 15);
        Travel travel4 = new Travel("Bilbao", "Madrid", new Date(2024, 04, 06, 14, 30), 15);

        entityManager.persistAndFlush(travel1);
        entityManager.persistAndFlush(travel2);
        entityManager.persistAndFlush(travel3);
        entityManager.persistAndFlush(travel4);

        List<Travel> travelsFound = travelRepository.findByDepartureAndArrival("Lisbon", "Madrid");

        assertThat(travelsFound).hasSize(2);
        assertThat(travelsFound.get(0)).isEqualTo(travel1);
        assertThat(travelsFound.get(1)).isEqualTo(travel2);
    }

    @Test
    // find Travels By Date
    void findByDate() {
        Travel travel1 = new Travel("Lisbon", "Madrid", new Date(2024, 04, 06, 14, 30), 15);
        Travel travel2 = new Travel("Lisbon", "Madrid", new Date(2024, 04, 07, 14, 30), 15);
        Travel travel3 = new Travel("Madrid", "Sevilla", new Date(2024, 04, 06, 14, 30), 15);
        Travel travel4 = new Travel("Bilbao", "Madrid", new Date(2024, 04, 06, 14, 30), 15);

        entityManager.persistAndFlush(travel1);
        entityManager.persistAndFlush(travel2);
        entityManager.persistAndFlush(travel3);
        entityManager.persistAndFlush(travel4);

        List<Travel> travelsFound = travelRepository.findByDate(new Date(2024, 04, 06), new Date(2024, 04, 07));

        assertThat(travelsFound).hasSize(3);
        assertThat(travelsFound.get(0)).isEqualTo(travel1);
        assertThat(travelsFound.get(1)).isEqualTo(travel3);
        assertThat(travelsFound.get(2)).isEqualTo(travel4);
    }
        

    @Test
    // find Travels By Departure, Arrival and Date
    void findByDepartureAndArrivalAndDate() {
        Travel travelMorning = new Travel("Lisbon", "Porto", new Date(2024, 04, 06, 10, 30), 11.5);
        Travel travelNoon = new Travel("Lisbon", "Porto", new Date(2024, 04, 06, 17, 30), 11.5);
        entityManager.persistAndFlush(travelMorning);
        entityManager.persistAndFlush(travelNoon);

        List<Travel> travelsFound = travelRepository.findByDepartureAndArrivalAndDate("Lisbon", "Porto",
                new Date(2024, 04, 06), new Date(2024, 04, 07));

        assertThat(travelsFound.get(0)).isEqualTo(travelMorning);
        assertThat(travelsFound.get(1)).isEqualTo(travelNoon);
    }

    @Test
    // return a Travel that does not exist
    void findByTravelIdNotFound() {
        Travel travel = new Travel("Lisbon", "Porto", new Date(2024, 04, 05, 14, 30), 11.5);
        entityManager.persistAndFlush(travel);

        Travel found = travelRepository.findByTravelId(999L);
        assertThat(found).isNull();
    }
}
