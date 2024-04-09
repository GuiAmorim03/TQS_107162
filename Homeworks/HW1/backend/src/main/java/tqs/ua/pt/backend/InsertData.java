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

    private static final String LISBON = "Lisbon";
    private static final String MADRID = "Madrid";
    private static final String PARIS = "Paris";

    private TravelRepository travelRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public InsertData(TravelRepository travelRepository, ReservationRepository reservationRepository) {
        this.travelRepository = travelRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Inserting data...");

        reservationRepository.deleteAll();
        travelRepository.deleteAll();

        travelRepository.save(new Travel(LISBON, MADRID, new Date(2024, 3, 13, 11, 30), 400, 30));
        travelRepository.save(new Travel(LISBON, MADRID, new Date(2024, 3, 13, 18, 30), 400, 30));
        travelRepository.save(new Travel(MADRID, LISBON, new Date(2024, 3, 13, 11, 30), 400, 30));
        travelRepository.save(new Travel(MADRID, LISBON, new Date(2024, 3, 13, 18, 30), 400, 30));
        travelRepository.save(new Travel(LISBON, MADRID, new Date(2024, 3, 14, 11, 30), 400, 30));
        travelRepository.save(new Travel(LISBON, MADRID, new Date(2024, 3, 14, 18, 30), 400, 30));
        travelRepository.save(new Travel(MADRID, LISBON, new Date(2024, 3, 14, 11, 30), 400, 30));
        travelRepository.save(new Travel(MADRID, LISBON, new Date(2024, 3, 14, 18, 30), 400, 30));
        
        travelRepository.save(new Travel(LISBON, PARIS, new Date(2024, 3, 13, 11, 30), 990, 20));
        travelRepository.save(new Travel(LISBON, PARIS, new Date(2024, 3, 13, 18, 30), 990, 20));
        travelRepository.save(new Travel(PARIS, LISBON, new Date(2024, 3, 13, 11, 30), 990, 20));
        travelRepository.save(new Travel(PARIS, LISBON, new Date(2024, 3, 13, 18, 30), 990, 20));
        travelRepository.save(new Travel(LISBON, PARIS, new Date(2024, 3, 14, 11, 30), 990, 20));
        travelRepository.save(new Travel(LISBON, PARIS, new Date(2024, 3, 14, 18, 30), 990, 20));
        travelRepository.save(new Travel(PARIS, LISBON, new Date(2024, 3, 14, 11, 30), 990, 20));
        travelRepository.save(new Travel(PARIS, LISBON, new Date(2024, 3, 14, 18, 30), 990, 20));

        travelRepository.save(new Travel(MADRID, PARIS, new Date(2024, 3, 13, 11, 30), 750, 25));
        travelRepository.save(new Travel(MADRID, PARIS, new Date(2024, 3, 13, 18, 30), 750, 25));
        travelRepository.save(new Travel(PARIS, MADRID, new Date(2024, 3, 13, 11, 30), 750, 25));
        travelRepository.save(new Travel(PARIS, MADRID, new Date(2024, 3, 13, 18, 30), 750, 25));
        travelRepository.save(new Travel(MADRID, PARIS, new Date(2024, 3, 14, 11, 30), 750, 25));
        travelRepository.save(new Travel(MADRID, PARIS, new Date(2024, 3, 14, 18, 30), 750, 25));
        travelRepository.save(new Travel(PARIS, MADRID, new Date(2024, 3, 14, 11, 30), 750, 25));
        travelRepository.save(new Travel(PARIS, MADRID, new Date(2024, 3, 14, 18, 30), 750, 25));

        logger.info("Data inserted!");
    }
    
}
