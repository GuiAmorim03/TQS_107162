package tqs.ua.pt.carsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    
    public Optional<Car> getCarDetails(Long carId) {
        return carRepository.findByCarId(carId);
    }

}
