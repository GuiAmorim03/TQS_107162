package tqs.ua.pt.backend.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.ua.pt.backend.models.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long>{
    public Travel findByTravelId(Long travelId);
    public List<Travel> findByDepartureAndArrivalAndDate(String departure, String arrival, Date date);
   
}
