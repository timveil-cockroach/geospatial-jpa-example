package io.crdb.geo.jpa;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class DataLoader implements ApplicationRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    private final AircraftRepository aircraftRepository;

    public DataLoader(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        GeometryFactory geometryFactory = new GeometryFactory();

        UUID id = UUID.randomUUID();

        Aircraft saved = aircraftRepository.save(new Aircraft(id, geometryFactory.createPoint(new Coordinate(-73.935242, 40.730610))));

        log.info("saved Aircraft with id {}", saved.getId());

        Optional<Aircraft> optional = aircraftRepository.findById(id);

        if (optional.isPresent()) {
            Aircraft found = optional.get();

            log.info("found Aircraft with id {}", found.getId());
        } else {
            log.warn("unable to find Aircraft with id {}", id);
        }
    }
}
