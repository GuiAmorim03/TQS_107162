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
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
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
class TravelControllerTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        private TravelService travelService;

        private Travel travel, anotherTravel, anotherTravelanotherDay;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        @BeforeEach
        public void setUp() {
                travel = new Travel("Lisbon", "Porto", new Date(2024, 4, 8, 11, 30, 0), 3 * 60 + 30, 12.5);
                anotherTravel = new Travel("Lisbon", "Porto", new Date(2024, 4, 8, 15, 30, 0), 4 * 60, 12.5);
                anotherTravelanotherDay = new Travel("Lisbon", "Porto", new Date(2024, 4, 9, 11, 30, 0), 3 * 60 + 30,
                                12.5);

                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        }

        @Test
        void whenPostTravel_thenCreateTravel() throws IOException, Exception {
                when(travelService.save(Mockito.any())).thenReturn(travel);

                String formattedDepartureDate = dateFormat.format(new Date(2024, 4, 8, 11, 30, 0));
                String formattedArrivalDate = dateFormat.format(new Date(2024, 4, 8, 15, 0, 0));

                // por motivos de correspondência com o formato retornado pelo endpoint, tive de
                // trocar o 'Z' por '+00:00'
                formattedDepartureDate = formattedDepartureDate.replaceAll("Z$", "+00:00");
                formattedArrivalDate = formattedArrivalDate.replaceAll("Z$", "+00:00");

                mvc.perform(post("/api/travel").contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.toJson(travel)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.departure", is("Lisbon")))
                                .andExpect(jsonPath("$.arrival", is("Porto")))
                                .andExpect(jsonPath("$.dateDeparture", is(formattedDepartureDate)))
                                .andExpect(jsonPath("$.dateArrival", is(formattedArrivalDate)))
                                .andExpect(jsonPath("$.price", is(12.5)));
        }

        @Test
        void whenGetTravelById_theReturnTravel() throws Exception {
                when(travelService.getTravelById(1L)).thenReturn(travel);

                String formattedDepartureDate = dateFormat.format(new Date(2024, 4, 8, 11, 30, 0));
                String formattedArrivalDate = dateFormat.format(new Date(2024, 4, 8, 15, 0, 0));

                formattedDepartureDate = formattedDepartureDate.replaceAll("Z$", "+00:00");
                formattedArrivalDate = formattedArrivalDate.replaceAll("Z$", "+00:00");

                mvc.perform(get("/api/travel/{id}", 1L).contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.departure", is("Lisbon")))
                                .andExpect(jsonPath("$.arrival", is("Porto")))
                                .andExpect(jsonPath("$.dateDeparture", is(formattedDepartureDate)))
                                .andExpect(jsonPath("$.dateArrival", is(formattedArrivalDate)))
                                .andExpect(jsonPath("$.price", is(12.5)));
        }

        @Test
        void whenGetTravelByDepartureAndArrival_thenReturnTravelsBetweenThoseCities() throws Exception {
                when(travelService.getTravelsByDepartureAndArrival("Lisbon", "Porto"))
                                .thenReturn(List.of(travel, anotherTravel, anotherTravelanotherDay));

                String formattedDepartureDate0 = dateFormat.format(new Date(2024, 4, 8, 11, 30, 0));
                String formattedDepartureDate1 = dateFormat.format(new Date(2024, 4, 8, 15, 30, 0));
                String formattedDepartureDate2 = dateFormat.format(new Date(2024, 4, 9, 11, 30, 0));
                String formattedArrivalDate0 = dateFormat.format(new Date(2024, 4, 8, 15, 0, 0));
                String formattedArrivalDate1 = dateFormat.format(new Date(2024, 4, 8, 19, 30, 0));
                String formattedArrivalDate2 = dateFormat.format(new Date(2024, 4, 9, 15, 0, 0));

                formattedDepartureDate0 = formattedDepartureDate0.replaceAll("Z$", "+00:00");
                formattedDepartureDate1 = formattedDepartureDate1.replaceAll("Z$", "+00:00");
                formattedDepartureDate2 = formattedDepartureDate2.replaceAll("Z$", "+00:00");
                formattedArrivalDate0 = formattedArrivalDate0.replaceAll("Z$", "+00:00");
                formattedArrivalDate1 = formattedArrivalDate1.replaceAll("Z$", "+00:00");
                formattedArrivalDate2 = formattedArrivalDate2.replaceAll("Z$", "+00:00");

                mvc.perform(
                                get("/api/travel/{departure}/{arrival}", "Lisbon", "Porto")
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", hasSize(3)))
                                .andExpect(jsonPath("$[0].departure", is("Lisbon")))
                                .andExpect(jsonPath("$[0].arrival", is("Porto")))
                                .andExpect(jsonPath("$[0].dateDeparture", is(formattedDepartureDate0)))
                                .andExpect(jsonPath("$[0].dateArrival", is(formattedArrivalDate0)))
                                .andExpect(jsonPath("$[0].price", is(12.5)))
                                .andExpect(jsonPath("$[1].departure", is("Lisbon")))
                                .andExpect(jsonPath("$[1].arrival", is("Porto")))
                                .andExpect(jsonPath("$[1].dateDeparture", is(formattedDepartureDate1)))
                                .andExpect(jsonPath("$[1].dateArrival", is(formattedArrivalDate1)))
                                .andExpect(jsonPath("$[1].price", is(12.5)))
                                .andExpect(jsonPath("$[2].departure", is("Lisbon")))
                                .andExpect(jsonPath("$[2].arrival", is("Porto")))
                                .andExpect(jsonPath("$[2].dateDeparture", is(formattedDepartureDate2)))
                                .andExpect(jsonPath("$[2].dateArrival", is(formattedArrivalDate2)))
                                .andExpect(jsonPath("$[2].price", is(12.5)));
        }

        @Test
        void whenGetTravelByDepartureAndArrivalAndDate_thenReturnTravelsBetweenThoseCitiesInThatDay() throws Exception {
                when(travelService.getTravelsByDepartureAndArrivalAndDate("Lisbon", "Porto", new Date(2024, 4, 8),
                                new Date(2024, 4, 9))).thenReturn(List.of(travel, anotherTravel));

                String formattedDepartureDate0 = dateFormat.format(new Date(2024, 4, 8, 11, 30, 0));
                String formattedDepartureDate1 = dateFormat.format(new Date(2024, 4, 8, 15, 30, 0));
                String formattedArrivalDate0 = dateFormat.format(new Date(2024, 4, 8, 15, 0, 0));
                String formattedArrivalDate1 = dateFormat.format(new Date(2024, 4, 8, 19, 30, 0));
                formattedDepartureDate0 = formattedDepartureDate0.replaceAll("Z$", "+00:00");
                formattedDepartureDate1 = formattedDepartureDate1.replaceAll("Z$", "+00:00");
                formattedArrivalDate0 = formattedArrivalDate0.replaceAll("Z$", "+00:00");
                formattedArrivalDate1 = formattedArrivalDate1.replaceAll("Z$", "+00:00");

                mvc.perform(
                                get("/api/travel/{departure}/{arrival}/{date}", "Lisbon", "Porto", "2024-05-08")
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", hasSize(2)))
                                .andExpect(jsonPath("$[0].departure", is("Lisbon")))
                                .andExpect(jsonPath("$[0].arrival", is("Porto")))
                                .andExpect(jsonPath("$[0].dateDeparture", is(formattedDepartureDate0)))
                                .andExpect(jsonPath("$[0].dateArrival", is(formattedArrivalDate0)))
                                .andExpect(jsonPath("$[0].price", is(12.5)))
                                .andExpect(jsonPath("$[1].departure", is("Lisbon")))
                                .andExpect(jsonPath("$[1].arrival", is("Porto")))
                                .andExpect(jsonPath("$[1].dateDeparture", is(formattedDepartureDate1)))
                                .andExpect(jsonPath("$[1].dateArrival", is(formattedArrivalDate1)))
                                .andExpect(jsonPath("$[1].price", is(12.5)));

        }

        @Test
        void whenGetDepartures_thenReturnAllDepartures() throws Exception {
                when(travelService.getAllDepartures()).thenReturn(List.of("Lisbon", "Madrid", "Paris"));

                mvc.perform(get("/api/departures").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", hasSize(3)))
                                .andExpect(jsonPath("$[0]", is("Lisbon")))
                                .andExpect(jsonPath("$[1]", is("Madrid")))
                                .andExpect(jsonPath("$[2]", is("Paris")));
        }

        @Test
        void whenGetArrivals_thenReturnAllArrivals() throws Exception {
                when(travelService.getAllArrivals()).thenReturn(List.of("Lisbon", "Madrid", "Paris"));

                mvc.perform(get("/api/arrivals").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", hasSize(3)))
                                .andExpect(jsonPath("$[0]", is("Lisbon")))
                                .andExpect(jsonPath("$[1]", is("Madrid")))
                                .andExpect(jsonPath("$[2]", is("Paris")));
        }

}
