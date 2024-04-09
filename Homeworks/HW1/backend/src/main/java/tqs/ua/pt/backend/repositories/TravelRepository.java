package tqs.ua.pt.backend.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tqs.ua.pt.backend.models.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    public Travel findByTravelId(Long travelId);

    public List<Travel> findByDepartureAndArrival(String departure, String arrival);

    @Query("SELECT t FROM Travel t WHERE t.departure = :departure AND t.arrival = :arrival AND t.dateDeparture >= :startDate AND t.dateDeparture < :endDate")
    public List<Travel> findByDepartureAndArrivalAndDate(@Param("departure") String departure,
            @Param("arrival") String arrival, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
