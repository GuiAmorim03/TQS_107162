package tqs.ua.pt.backend.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.repositories.TravelRepository;

@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
public class TravelServiceTest {

    @Mock(lenient = true)
    private TravelRepository travelRepository;

    @InjectMocks
    private TravelService travelService;

    private Travel iberian, centralEurope, uk, portuguese1, portuguese2, portuguese3;
    

    @BeforeEach
    public void setUp() {

        iberian = new Travel("Lisbon", "Madrid", new Date(2024, 4, 7, 14, 30), 13.5);
        iberian.setTravelId(1L);
        centralEurope = new Travel("Berlin", "Prague", new Date(2024, 4, 8, 11, 30), 8.5);
        uk = new Travel("London", "Edinburgh", new Date(2024, 4, 7, 10, 30), 10.5);

        portuguese1 = new Travel("Lisbon", "Porto", new Date(2024, 4, 5, 14, 30), 11.5);
        portuguese2 = new Travel("Lisbon", "Porto", new Date(2024, 4, 5, 18, 30), 11.5);
        portuguese3 = new Travel("Lisbon", "Porto", new Date(2024, 4, 6, 14, 30), 11.5);
    }

    @Test
    // create new travel
    void testSaveTravel() {
        Mockito.when(travelRepository.save(iberian)).thenReturn(iberian);

        assertThat(travelService.save(iberian).getDeparture()).isEqualTo("Lisbon");
        assertThat(travelService.save(iberian).getArrival()).isEqualTo("Madrid");
        assertThat(travelService.save(iberian).getDate()).isEqualTo(new Date(2024, 4, 7, 14, 30));
        assertThat(travelService.save(iberian).getPrice()).isEqualTo(13.5);
        assertThat(travelService.save(iberian).getSeats()).isEqualTo(0);
    }

    @Test
    // find Travel By Id
    void whenSearchTravelById_thenReturnTravel() {
        Mockito.when(travelRepository.findByTravelId(iberian.getTravelId())).thenReturn(iberian);

        Long id = 1L;
        Travel travel = travelService.getTravelById(id);

        assertThat(travel.getDeparture()).isEqualTo("Lisbon");
        assertThat(travel.getArrival()).isEqualTo("Madrid");
        assertThat(travel.getDate()).isEqualTo(new Date(2024, 4, 7, 14, 30));
        assertThat(travel.getPrice()).isEqualTo(13.5);
        assertThat(travel.getSeats()).isEqualTo(0);
    }

    @Test
    // find Travel By Id that does not exist
    void whenSearchInvalidId_thenTravelShouldBeNotFound() {
        Mockito.when(travelRepository.findByTravelId(-99L)).thenReturn(null);

        Long id = -99L;
        assertThat(travelService.getTravelById(id)).isNull();
    }

    @Test
    // get Travels By Departure and Arrival for any date
    void whenSearchByDepartureAndArrival_thenReturnTravelsForAllDates() {
        Mockito.when(travelRepository.findByDepartureAndArrival("Lisbon", "Porto")).thenReturn(Arrays.asList(portuguese1, portuguese2, portuguese3));

        List<Travel> travels = travelService.getTravelsByDepartureAndArrival("Lisbon", "Porto");

        assertThat(travels.size()).isEqualTo(3);
        for (int i = 0; i < travels.size(); i++) {
            assertThat(travels.get(i).getDeparture()).isEqualTo("Lisbon");
            assertThat(travels.get(i).getArrival()).isEqualTo("Porto");
            assertThat(travels.get(i).getPrice()).isEqualTo(11.5);
            assertThat(travels.get(i).getSeats()).isEqualTo(0);
        }
        assertThat(travels.get(0).getDate()).isEqualTo(new Date(2024, 4, 5, 14, 30));
        assertThat(travels.get(1).getDate()).isEqualTo(new Date(2024, 4, 5, 18, 30));
        assertThat(travels.get(2).getDate()).isEqualTo(new Date(2024, 4, 6, 14, 30));
    }

    @Test
    // get Travels By Departure and Arrival for a specific date
    void whenSearchByDepartureAndArrivalAndDate_thenReturnTravelsForThatDate() {
        Mockito.when(travelRepository.findByDepartureAndArrivalAndDate("Lisbon", "Porto", new Date(2024, 4, 5), new Date(2024, 4, 6))).thenReturn(Arrays.asList(portuguese1, portuguese2));

        List<Travel> travels = travelService.getTravelsByDepartureAndArrivalAndDate("Lisbon", "Porto", new Date(2024, 4, 5), new Date(2024, 4, 6));

        assertThat(travels.size()).isEqualTo(2);
        for (int i = 0; i < travels.size(); i++) {
            assertThat(travels.get(i).getDeparture()).isEqualTo("Lisbon");
            assertThat(travels.get(i).getArrival()).isEqualTo("Porto");
            assertThat(travels.get(i).getPrice()).isEqualTo(11.5);
            assertThat(travels.get(i).getSeats()).isEqualTo(0);
        }
        assertThat(travels.get(0).getDate()).isEqualTo(new Date(2024, 4, 5, 14, 30));
        assertThat(travels.get(1).getDate()).isEqualTo(new Date(2024, 4, 5, 18, 30));
    }

    @Test
    // get all Travels
    void testGetAllTravels() {
        Mockito.when(travelRepository.findAll()).thenReturn(Arrays.asList(iberian, centralEurope, uk, portuguese1, portuguese2, portuguese3));

        List<Travel> travels = travelService.getAllTravels();


        assertThat(travels.size()).isEqualTo(6);
        
        assertThat(travels.get(0).getDeparture()).isEqualTo("Lisbon");
        assertThat(travels.get(0).getArrival()).isEqualTo("Madrid");
        assertThat(travels.get(0).getDate()).isEqualTo(new Date(2024, 4, 7, 14, 30));
        assertThat(travels.get(0).getPrice()).isEqualTo(13.5);

        assertThat(travels.get(1).getDeparture()).isEqualTo("Berlin");
        assertThat(travels.get(1).getArrival()).isEqualTo("Prague");
        assertThat(travels.get(1).getDate()).isEqualTo(new Date(2024, 4, 8, 11, 30));
        assertThat(travels.get(1).getPrice()).isEqualTo(8.5);

        assertThat(travels.get(2).getDeparture()).isEqualTo("London");
        assertThat(travels.get(2).getArrival()).isEqualTo("Edinburgh");
        assertThat(travels.get(2).getDate()).isEqualTo(new Date(2024, 4, 7, 10, 30));
        assertThat(travels.get(2).getPrice()).isEqualTo(10.5);

        assertThat(travels.get(3).getDeparture()).isEqualTo("Lisbon");
        assertThat(travels.get(3).getArrival()).isEqualTo("Porto");
        assertThat(travels.get(3).getDate()).isEqualTo(new Date(2024, 4, 5, 14, 30));
        assertThat(travels.get(3).getPrice()).isEqualTo(11.5);

        assertThat(travels.get(4).getDeparture()).isEqualTo("Lisbon");
        assertThat(travels.get(4).getArrival()).isEqualTo("Porto");
        assertThat(travels.get(4).getDate()).isEqualTo(new Date(2024, 4, 5, 18, 30));
        assertThat(travels.get(4).getPrice()).isEqualTo(11.5);

        assertThat(travels.get(5).getDeparture()).isEqualTo("Lisbon");
        assertThat(travels.get(5).getArrival()).isEqualTo("Porto");
        assertThat(travels.get(5).getDate()).isEqualTo(new Date(2024, 4, 6, 14, 30));
        assertThat(travels.get(5).getPrice()).isEqualTo(11.5);

        for (int i = 0; i<travels.size(); i++) {
            assertThat(travels.get(i).getSeats()).isEqualTo(0);
        }
    }

    @Test
    // get all Departures
    void testGetAllDepartures() {
        Mockito.when(travelRepository.findAll()).thenReturn(Arrays.asList(iberian, centralEurope, uk, portuguese1, portuguese2, portuguese3));

        List<String> departures = travelService.getAllDepartures();

        assertThat(departures.size()).isEqualTo(3);
        assertThat(departures.get(0)).isEqualTo("Lisbon");
        assertThat(departures.get(1)).isEqualTo("Berlin");
        assertThat(departures.get(2)).isEqualTo("London");
    }

    @Test
    // get all Arrivals
    void testGetAllArrivals() {
        Mockito.when(travelRepository.findAll()).thenReturn(Arrays.asList(iberian, centralEurope, uk, portuguese1, portuguese2, portuguese3));

        List<String> arrivals = travelService.getAllArrivals();

        assertThat(arrivals.size()).isEqualTo(4);
        assertThat(arrivals.get(0)).isEqualTo("Madrid");
        assertThat(arrivals.get(1)).isEqualTo("Prague");
        assertThat(arrivals.get(2)).isEqualTo("Edinburgh");
        assertThat(arrivals.get(3)).isEqualTo("Porto");
    }

}
