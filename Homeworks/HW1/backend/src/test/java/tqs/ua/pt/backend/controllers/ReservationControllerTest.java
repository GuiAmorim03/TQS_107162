package tqs.ua.pt.backend.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tqs.ua.pt.backend.models.Reservation;
import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.services.ReservationService;
import tqs.ua.pt.backend.utils.JsonUtils;

@SuppressWarnings("deprecation")
@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    private Reservation reservation;
    private Travel travel;

    @BeforeEach
    public void setUp() {
        travel = new Travel("Lisbon", "Madrid", new Date(2024, 4, 7, 14, 30), 450, 30);
        reservation = new Reservation(travel, "Cliente Aleatório", "client@a.pt", "987654321", "123456789",
                "Rua dos Testes, Aveiro, nº3", "MB Way", "987654321", 3, "EUR");
    }

    @Test
    void whenPostReservation_thenCreateReservation() throws IOException, Exception {
        reservation.getTravel().addSeats(3); // simular a compra de 3 bilhetes
        when(reservationService.save(Mockito.any())).thenReturn(reservation);

        mvc.perform(post("/api/reservation").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(reservation)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.travel.departure", is("Lisbon")))
                .andExpect(jsonPath("$.travel.arrival", is("Madrid")))
                .andExpect(jsonPath("$.travel.price", is(30.0)))
                .andExpect(jsonPath("$.travel.seats", is(22)))
                .andExpect(jsonPath("$.name", is("Cliente Aleatório")))
                .andExpect(jsonPath("$.email", is("client@a.pt")))
                .andExpect(jsonPath("$.phone", is("987654321")))
                .andExpect(jsonPath("$.nif", is("123456789")))
                .andExpect(jsonPath("$.address", is("Rua dos Testes, Aveiro, nº3")))
                .andExpect(jsonPath("$.paymentMethod", is("MB Way")))
                .andExpect(jsonPath("$.paymentNumber", is("987654321")))
                .andExpect(jsonPath("$.qtt", is(3)));
    }

    @Test
    void whenGetTravelById_theReturnTravel() throws Exception {
        when(reservationService.getReservationByToken(1L)).thenReturn(reservation);

        mvc.perform(get("/api/reservation/{token}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.travel.departure", is("Lisbon")))
                .andExpect(jsonPath("$.travel.arrival", is("Madrid")))
                .andExpect(jsonPath("$.travel.price", is(30.0)))
                .andExpect(jsonPath("$.travel.seats", is(25)))
                .andExpect(jsonPath("$.name", is("Cliente Aleatório")))
                .andExpect(jsonPath("$.email", is("client@a.pt")))
                .andExpect(jsonPath("$.phone", is("987654321")))
                .andExpect(jsonPath("$.nif", is("123456789")))
                .andExpect(jsonPath("$.address", is("Rua dos Testes, Aveiro, nº3")))
                .andExpect(jsonPath("$.paymentMethod", is("MB Way")))
                .andExpect(jsonPath("$.paymentNumber", is("987654321")))
                .andExpect(jsonPath("$.qtt", is(3)));
    }
}
