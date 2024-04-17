package tqs.ua.pt.carsservice;

import static org.mockito.Mockito.*;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CarController.class)
public class CarControllerNewTest {
    
    @MockBean
    CarManagerService carManagerService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200() {
        Car car = new Car("Audi", "A3");
        when(carManagerService.save(Mockito.any())).thenReturn(car);

        RestAssuredMockMvc.when()
                .get("/api/cars")
                .then()
                .statusCode(200);
    }

}
