package tqs.ua.pt.backend.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public Travel getTravelById(@PathVariable("id") Long travelId) {
        return travelService.getTravelById(travelId);
    }

    @GetMapping("/travel/{departure}/{arrival}/{date}")
    public List<Travel> getTravelByDepartureAndArrivalAndDate(@PathVariable("departure") String departure, @PathVariable("arrival") String arrival,
            @PathVariable("date") String date) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1); // Avançar 1 dia
        Date endDate = calendar.getTime();

        return travelService.getTravelsByDepartureAndArrivalAndDate(departure, arrival, startDate, endDate);
    }

    @GetMapping("/travel/{departure}/{arrival}")
    public List<Travel> getTravelByDepartureAndArrival(@PathVariable String departure, @PathVariable String arrival) throws ParseException {


        return travelService.getTravelsByDepartureAndArrival(departure, arrival);
    }
}
