package tqs.ua.pt.carsservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.restassured.module.mockmvc.RestAssuredMockMvc;


@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        Car bmw = new Car("bmw", "i8");

        when(service.save(Mockito.any())).thenReturn(bmw);

        mvc.perform(
                post("/api/car").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(bmw)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("i8")));

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car bmw = new Car("bmw", "i8");
        Car benz = new Car("benz", "cls");
        Car audi = new Car("audi", "a5");

        List<Car> allCars = Arrays.asList(bmw, benz, audi);

        when(service.getAllCars()).thenReturn(allCars);

        mvc.perform(
                get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(bmw.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(benz.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(audi.getMaker())));

        verify(service, times(1)).getAllCars();
    }

    @Test
    public void testRestAssuredMockMvc() {
        RestAssuredMockMvc
                .given()
                .mockMvc(mockMvc)
                .when().get("/api/cars");
    }

}