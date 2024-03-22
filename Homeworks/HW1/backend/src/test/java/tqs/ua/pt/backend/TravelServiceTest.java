package tqs.ua.pt.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.repositories.TravelRepository;
import tqs.ua.pt.backend.services.TravelService;

@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
public class TravelServiceTest {

    @Mock(lenient = true)
    private TravelRepository travelRepository;

    @InjectMocks
    private TravelService travelService;
    

    @BeforeEach
    public void setUp() {

        Travel iberian = new Travel("Lisbon", "Madrid", new Date(2024, 4, 7, 14, 30), 13.5);
        iberian.setTravelId(1L);
        Travel centralEurope = new Travel("Berlin", "Prague", new Date(2024, 4, 8, 11, 30), 8.5);
        Travel uk = new Travel("London", "Edinburgh", new Date(2024, 4, 7, 10, 30), 10.5);
        Travel southEurope = new Travel("Rome", "Prague", new Date(2024, 4, 8, 12, 30), 15.5);

        List<Travel> allTravels = Arrays.asList(iberian, centralEurope, uk, southEurope);

        Mockito.when(travelRepository.findByTravelId(iberian.getTravelId())).thenReturn(iberian);
        Mockito.when(travelRepository.findByTravelId(centralEurope.getTravelId())).thenReturn(centralEurope);
        Mockito.when(travelRepository.findByTravelId(uk.getTravelId())).thenReturn(uk);
        Mockito.when(travelRepository.findByTravelId(southEurope.getTravelId())).thenReturn(southEurope);
        Mockito.when(travelRepository.findByTravelId(-99L)).thenReturn(null);

        Mockito.when(travelRepository.findAll()).thenReturn(allTravels);
    }

    @Test
    // create new travel
    void testSaveTravel() {
        Travel travel = new Travel("Lisbon", "Porto", new Date(2024, 4, 5, 14, 30), 11.5);

        Mockito.when(travelRepository.save(travel)).thenReturn(travel);

        assertThat(travelService.save(travel).getDeparture()).isEqualTo("Lisbon");
        assertThat(travelService.save(travel).getArrival()).isEqualTo("Porto");
    }

    @Test
    // find Travel By Id
    void whenSearchTravelById_thenReturnTravel() {
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
        Long id = -99L;
        assertThat(travelService.getTravelById(id)).isNull();
    }

    @Test
    // get all Travels
    void testGetAllTravels() {
        List<Travel> travels = travelService.getAllTravels();

        assertThat(travels.size()).isEqualTo(4);
        
        assertThat(travels.get(0).getDeparture()).isEqualTo("Lisbon");
        assertThat(travels.get(0).getArrival()).isEqualTo("Madrid");
        assertThat(travels.get(1).getDeparture()).isEqualTo("Berlin");
        assertThat(travels.get(1).getArrival()).isEqualTo("Prague");
        assertThat(travels.get(2).getDeparture()).isEqualTo("London");
        assertThat(travels.get(2).getArrival()).isEqualTo("Edinburgh");
        assertThat(travels.get(3).getDeparture()).isEqualTo("Rome");
        assertThat(travels.get(3).getArrival()).isEqualTo("Prague");
    }

}
