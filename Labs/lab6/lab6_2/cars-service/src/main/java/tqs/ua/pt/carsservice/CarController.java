package tqs.ua.pt.carsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarManagerService carManagerService;

    @Autowired
    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }


    @PostMapping("/car")
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        return new ResponseEntity<Car>(carManagerService.save(car), HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/car/{id}")
    public Optional<Car> getCarDetails(@PathVariable("id") Long carId) {
        return carManagerService.getCarDetails(carId);
    }
    
}
