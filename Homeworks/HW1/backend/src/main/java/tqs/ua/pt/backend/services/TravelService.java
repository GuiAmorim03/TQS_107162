package tqs.ua.pt.backend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.repositories.TravelRepository;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    public Travel save(Travel travel) {
        return travelRepository.save(travel);
    }

    public Travel getTravelById(Long travelId) {
        return travelRepository.findByTravelId(travelId);
    }

    public List<Travel> getTravelsByDepartureAndArrival(String departure, String arrival) {
        return travelRepository.findByDepartureAndArrival(departure, arrival);
    }

    public List<Travel> getTravelsByDepartureAndArrivalAndDate(String departure, String arrival, Date dateStart, Date dateEnd) {
        return travelRepository.findByDepartureAndArrivalAndDate(departure, arrival, dateStart, dateEnd);
    }

    public List<Travel> getAllTravels() {
        return travelRepository.findAll();
    }

    public List<String> getAllDepartures() {
        List<Travel> travels = travelRepository.findAll();
        List<String> departures = new ArrayList<>();
        
        for (Travel travel : travels) {
            if (!departures.contains(travel.getDeparture())) {
                departures.add(travel.getDeparture());
            }
        }

        return departures;
    }

    public List<String> getAllArrivals() {
        List<Travel> travels = travelRepository.findAll();
        List<String> arrivals = new ArrayList<>();
        
        for (Travel travel : travels) {
            if (!arrivals.contains(travel.getArrival())) {
                arrivals.add(travel.getArrival());
            }
        }

        return arrivals;
    }

}
