package tqs.ua.pt.backend;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tqs.ua.pt.backend.models.Travel;
import tqs.ua.pt.backend.repositories.ReservationRepository;
import tqs.ua.pt.backend.repositories.TravelRepository;

@SuppressWarnings("deprecation")
@Component
public class InsertData implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InsertData.class);

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void run(String... args) throws Exception {

        logger.info("Inserting data...");

        reservationRepository.deleteAll();
        travelRepository.deleteAll();

        travelRepository.save(new Travel("Lisbon", "Madrid", new Date(2024, 3, 13, 11, 30), 400, 30));
        travelRepository.save(new Travel("Lisbon", "Madrid", new Date(2024, 3, 13, 18, 30), 400, 30));
        travelRepository.save(new Travel("Madrid", "Lisbon", new Date(2024, 3, 13, 11, 30), 400, 30));
        travelRepository.save(new Travel("Madrid", "Lisbon", new Date(2024, 3, 13, 18, 30), 400, 30));
        travelRepository.save(new Travel("Lisbon", "Madrid", new Date(2024, 3, 14, 11, 30), 400, 30));
        travelRepository.save(new Travel("Lisbon", "Madrid", new Date(2024, 3, 14, 18, 30), 400, 30));
        travelRepository.save(new Travel("Madrid", "Lisbon", new Date(2024, 3, 14, 11, 30), 400, 30));
        travelRepository.save(new Travel("Madrid", "Lisbon", new Date(2024, 3, 14, 18, 30), 400, 30));
        
        travelRepository.save(new Travel("Lisbon", "Paris", new Date(2024, 3, 13, 11, 30), 990, 20));
        travelRepository.save(new Travel("Lisbon", "Paris", new Date(2024, 3, 13, 18, 30), 990, 20));
        travelRepository.save(new Travel("Paris", "Lisbon", new Date(2024, 3, 13, 11, 30), 990, 20));
        travelRepository.save(new Travel("Paris", "Lisbon", new Date(2024, 3, 13, 18, 30), 990, 20));
        travelRepository.save(new Travel("Lisbon", "Paris", new Date(2024, 3, 14, 11, 30), 990, 20));
        travelRepository.save(new Travel("Lisbon", "Paris", new Date(2024, 3, 14, 18, 30), 990, 20));
        travelRepository.save(new Travel("Paris", "Lisbon", new Date(2024, 3, 14, 11, 30), 990, 20));
        travelRepository.save(new Travel("Paris", "Lisbon", new Date(2024, 3, 14, 18, 30), 990, 20));

        travelRepository.save(new Travel("Madrid", "Paris", new Date(2024, 3, 13, 11, 30), 750, 25));
        travelRepository.save(new Travel("Madrid", "Paris", new Date(2024, 3, 13, 18, 30), 750, 25));
        travelRepository.save(new Travel("Paris", "Madrid", new Date(2024, 3, 13, 11, 30), 750, 25));
        travelRepository.save(new Travel("Paris", "Madrid", new Date(2024, 3, 13, 18, 30), 750, 25));
        travelRepository.save(new Travel("Madrid", "Paris", new Date(2024, 3, 14, 11, 30), 750, 25));
        travelRepository.save(new Travel("Madrid", "Paris", new Date(2024, 3, 14, 18, 30), 750, 25));
        travelRepository.save(new Travel("Paris", "Madrid", new Date(2024, 3, 14, 11, 30), 750, 25));
        travelRepository.save(new Travel("Paris", "Madrid", new Date(2024, 3, 14, 18, 30), 750, 25));

        logger.info("Data inserted!");
    }
    
}
