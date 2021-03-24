package com.example.demo;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataLoader implements ApplicationRunner {

    private final AircraftRepository aircraftRepository;

    public DataLoader(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        GeometryFactory geometryFactory = new GeometryFactory();

        aircraftRepository.save(new Aircraft(UUID.randomUUID().toString(), geometryFactory.createPoint(new Coordinate(-73.935242, 40.730610))));
    }
}
