package tqs.ua.pt.carsservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    
    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {

        Car bmw = new Car("bmw", "i8");
        bmw.setCarId(111L);

        Car benz = new Car("benz", "cls");
        Car audi = new Car("audi", "a5");

        List<Car> allCars = Arrays.asList(bmw, benz, audi);

        Mockito.when(carRepository.findByCarId(bmw.getCarId())).thenReturn(Optional.of(bmw));
        Mockito.when(carRepository.findByCarId(benz.getCarId())).thenReturn(Optional.of(benz));
        Mockito.when(carRepository.findByCarId(audi.getCarId())).thenReturn(Optional.of(audi));
        Mockito.when(carRepository.findByCarId(-99L)).thenReturn(null);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
    void whenSearchInvalidId_thenCarShouldBeNotFound() {
        Long id = -99L;
        assertThat(carService.getCarDetails(id)).isNull();
    }

    @Test
    void testGetAllCars() {
        List<Car> cars = carService.getAllCars();

        assertThat(cars.size()).isEqualTo(3);
        
        assertThat(cars.get(0).getModel()).isEqualTo("i8");
        assertThat(cars.get(1).getModel()).isEqualTo("cls");
        assertThat(cars.get(2).getModel()).isEqualTo("a5");
    }

    @Test
    void testSaveCar() {
        Car car = new Car("toyota", "corolla");

        Mockito.when(carRepository.save(car)).thenReturn(car);

        assertThat(carService.save(car).getModel()).isEqualTo("corolla");
    }
   
}
