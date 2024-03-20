package tqs.ua.pt.backend.services;

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

    public List<Travel> getTravelsByDepartureAndArrivalAndDate(String departure, String arrival, String date) {
        return travelRepository.findByDepartureAndArrivalAndDate(departure, arrival, date);
    }
}
