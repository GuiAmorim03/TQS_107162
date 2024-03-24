package tqs.ua.pt.backend.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.services.TravelService;
import tqs.ua.pt.backend.utils.JsonUtils;

@SuppressWarnings("deprecation")
@WebMvcTest(TravelController.class)
public class TravelControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TravelService travelService;

    private Travel travel;

    @BeforeEach
    public void setUp() {
        travel = new Travel("Lisbon", "Porto", new Date(2024, 4, 8, 11, 30, 0), 12.5);
    }

    @Test
    void whenPostTravel_thenCreateTravel() throws IOException, Exception {
        when(travelService.save(Mockito.any())).thenReturn(travel);

        // necessário converter a data para o formato ISO 8601, para corresponder ao
        // formato retornado pelo endpoint
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = dateFormat.format(new Date(2024, 4, 8, 11, 30, 0));

        // por motivos de correspondência com o formato retornado pelo endpoint, tive de
        // trocar o 'Z' por '+00:00'
        formattedDate = formattedDate.replaceAll("Z$", "+00:00");

        mvc.perform(post("/api/travel").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(travel)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.departure", is("Lisbon")))
                .andExpect(jsonPath("$.arrival", is("Porto")))
                .andExpect(jsonPath("$.date", is(formattedDate)))
                .andExpect(jsonPath("$.price", is(12.5)));
    }

    @Test
    void whenGetTravelById_theReturnTravel() throws Exception {
        when(travelService.getTravelById(1L)).thenReturn(travel);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = dateFormat.format(new Date(2024, 4, 8, 11, 30, 0));
        formattedDate = formattedDate.replaceAll("Z$", "+00:00");

        mvc.perform(get("/api/travel/{id}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departure", is("Lisbon")))
                .andExpect(jsonPath("$.arrival", is("Porto")))
                .andExpect(jsonPath("$.date", is(formattedDate)))
                .andExpect(jsonPath("$.price", is(12.5)));
    }

    @Test
    @Disabled
    void whenGetTravelByDepartureAndArrivalAndDate_thenReturnTravelsBetweenThoseCitiesInThatDay() throws Exception {

        // Simular com novas viagens
        Travel anotherTravel = new Travel("Lisbon", "Porto", new Date(2024, 4, 8, 15, 30, 0), 12.5);
        List<Travel> travelList = new ArrayList<>();
        travelList.add(travel);
        travelList.add(anotherTravel);

        when(travelService.getTravelsByDepartureAndArrivalAndDate("Lisbon", "Porto", new Date(2024, 4, 8, 0, 0, 0),
                new Date(2024, 4, 9, 0, 0, 0))).thenReturn(travelList);

        mvc.perform(
                get("/api/travel/{departure}/{arrival}/{date}", "Lisbon", "Porto", "2024-05-08")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].departure", is("Lisbon")))
                .andExpect(jsonPath("$[0].arrival", is("Porto")))
                .andExpect(jsonPath("$[0].date", is("2024-05-08T11:30:00.000+00:00")))
                .andExpect(jsonPath("$[0].price", is(12.5)))
                .andExpect(jsonPath("$[1].departure", is("Lisbon")))
                .andExpect(jsonPath("$[1].arrival", is("Porto")))
                .andExpect(jsonPath("$[1].date", is("2024-05-08T15:30:00.000+00:00")))
                .andExpect(jsonPath("$[1].price", is(12.5)));

    }

    @Test
    @Disabled
    void whenGetTravelByDepartureAndArrival_thenReturnTravelsBetweenThoseCities() throws Exception {
        // to do
    }

}
