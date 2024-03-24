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
public class TravelControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TravelService travelService;

    private Travel travel, anotherTravel, anotherTravelanotherDay;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @BeforeEach
    public void setUp() {
        travel = new Travel("Lisbon", "Porto", new Date(2024, 4, 8, 11, 30, 0), 12.5);
        anotherTravel = new Travel("Lisbon", "Porto", new Date(2024, 4, 8, 15, 30, 0), 12.5);
        anotherTravelanotherDay = new Travel("Lisbon", "Porto", new Date(2024, 4, 9, 11, 30, 0), 12.5);

        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

    }

    @Test
    void whenPostTravel_thenCreateTravel() throws IOException, Exception {
        when(travelService.save(Mockito.any())).thenReturn(travel);

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
    void whenGetTravelByDepartureAndArrival_thenReturnTravelsBetweenThoseCities() throws Exception {
        when(travelService.getTravelsByDepartureAndArrival("Lisbon", "Porto"))
                .thenReturn(List.of(travel, anotherTravel, anotherTravelanotherDay));

        String formattedDate0 = dateFormat.format(new Date(2024, 4, 8, 11, 30, 0));
        String formattedDate1 = dateFormat.format(new Date(2024, 4, 8, 15, 30, 0));
        String formattedDate2 = dateFormat.format(new Date(2024, 4, 9, 11, 30, 0));
        formattedDate0 = formattedDate0.replaceAll("Z$", "+00:00");
        formattedDate1 = formattedDate1.replaceAll("Z$", "+00:00");
        formattedDate2 = formattedDate2.replaceAll("Z$", "+00:00");

        mvc.perform(
                get("/api/travel/{departure}/{arrival}", "Lisbon", "Porto")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].departure", is("Lisbon")))
                .andExpect(jsonPath("$[0].arrival", is("Porto")))
                .andExpect(jsonPath("$[0].date", is(formattedDate0)))
                .andExpect(jsonPath("$[0].price", is(12.5)))
                .andExpect(jsonPath("$[1].departure", is("Lisbon")))
                .andExpect(jsonPath("$[1].arrival", is("Porto")))
                .andExpect(jsonPath("$[1].date", is(formattedDate1)))
                .andExpect(jsonPath("$[1].price", is(12.5)))
                .andExpect(jsonPath("$[2].departure", is("Lisbon")))
                .andExpect(jsonPath("$[2].arrival", is("Porto")))
                .andExpect(jsonPath("$[2].date", is(formattedDate2)))
                .andExpect(jsonPath("$[2].price", is(12.5)));
    }

    @Test
    void whenGetTravelByDepartureAndArrivalAndDate_thenReturnTravelsBetweenThoseCitiesInThatDay() throws Exception {
        when(travelService.getTravelsByDepartureAndArrivalAndDate("Lisbon", "Porto", new Date(2024, 4, 8),
                new Date(2024, 4, 9))).thenReturn(List.of(travel, anotherTravel));

        System.out.println("limites de datas: ");
        System.out.println(new Date(2024, 4, 8));
        System.out.println(new Date(2024, 4, 9));

        String formattedDate0 = dateFormat.format(new Date(2024, 4, 8, 11, 30, 0));
        String formattedDate1 = dateFormat.format(new Date(2024, 4, 8, 15, 30, 0));
        formattedDate0 = formattedDate0.replaceAll("Z$", "+00:00");
        formattedDate1 = formattedDate1.replaceAll("Z$", "+00:00");

        mvc.perform(
                get("/api/travel/{departure}/{arrival}/{date}", "Lisbon", "Porto", "2024-05-08")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].departure", is("Lisbon")))
                .andExpect(jsonPath("$[0].arrival", is("Porto")))
                .andExpect(jsonPath("$[0].date", is(formattedDate0)))
                .andExpect(jsonPath("$[0].price", is(12.5)))
                .andExpect(jsonPath("$[1].departure", is("Lisbon")))
                .andExpect(jsonPath("$[1].arrival", is("Porto")))
                .andExpect(jsonPath("$[1].date", is(formattedDate1)))
                .andExpect(jsonPath("$[1].price", is(12.5)));

    }

}
