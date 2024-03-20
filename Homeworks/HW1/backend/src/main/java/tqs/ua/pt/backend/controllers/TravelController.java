package tqs.ua.pt.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.services.TravelService;

@RestController
@RequestMapping("/api")
public class TravelController {
    
    @Autowired
    private TravelService travelService;

    @PostMapping("/travel")
    public ResponseEntity<Travel> saveTravel(@RequestBody Travel travel) {
        return new ResponseEntity<Travel>(travelService.save(travel), HttpStatus.CREATED);
    }

    @GetMapping("/travel/{id}")
    public Travel getTravelById(@PathVariable Long travelId) {
        return travelService.getTravelById(travelId); 
    }

    @GetMapping("/travel/{departure}/{arrival}/{date}")
    public List<Travel> getTravelByRouteAndDate(@PathVariable String departure, @PathVariable String arrival, @PathVariable String date) {
        return travelService.getTravelsByDepartureAndArrivalAndDate(departure, arrival, date);
    }
}
