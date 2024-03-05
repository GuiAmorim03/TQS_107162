package tqs.ua.pt.carsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindBmwById_thenReturnBmwCar() {

        Car bmw = new Car("bmw", "i8");
        entityManager.persistAndFlush(bmw); 

        Optional<Car> found = carRepository.findByCarId(bmw.getCarId());
        assertThat(found).isEqualTo(Optional.of(bmw));
    }

    @Test
    void whenInvalidCarId_thenReturnEmpty() {
        Optional<Car> fromDb = carRepository.findByCarId(-99L);
        assertThat(fromDb).isEmpty();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car bmw = new Car("bmw", "i8");
        Car benz = new Car("benz", "cls");
        Car audi = new Car("audi", "a5");

        entityManager.persist(bmw);
        entityManager.persist(benz);
        entityManager.persist(audi);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getMaker).containsOnly(bmw.getMaker(), benz.getMaker(), audi.getMaker());
    }


}
