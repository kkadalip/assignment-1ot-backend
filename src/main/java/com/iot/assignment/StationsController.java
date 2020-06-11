package com.iot.assignment;

import com.iot.assignment.model.weather.display.ObservationsRepository;
import com.iot.assignment.model.weather.xml.ObservationsUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StationsController {
    private final ObservationsRepository observationsRepository;

    @Autowired
    public StationsController(ObservationsRepository observationsRepository) {
        this.observationsRepository = observationsRepository;
    }

    @CrossOrigin //NOSONAR
    @GetMapping("/stations")
    public List<ObservationsUI> getAllStations() {
        return new ArrayList<>(observationsRepository.findAll());
    }
}