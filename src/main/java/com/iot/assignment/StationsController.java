package com.iot.assignment;

import com.iot.assignment.model.weather.display.RepositoryForecasts;
import com.iot.assignment.model.weather.display.RepositoryObservations;
import com.iot.assignment.model.weather.xml.forecasts.ui.ForecastsUI;
import com.iot.assignment.model.weather.xml.observations.ui.ObservationsUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StationsController {
    private final RepositoryObservations repositoryObservations;
    private final RepositoryForecasts repositoryForecasts;

    @Autowired
    public StationsController(RepositoryObservations observationsRepository, RepositoryForecasts repositoryForecasts) {
        this.repositoryObservations = observationsRepository;
        this.repositoryForecasts = repositoryForecasts;
    }

    @CrossOrigin //NOSONAR
    @GetMapping("/api/stations")
    public List<ObservationsUI> getAllStations() {
        return new ArrayList<>(repositoryObservations.findAll());
    }

    @CrossOrigin //NOSONAR
    @GetMapping("/api/forecasts")
    public List<ForecastsUI> getAllForecasts() {
        return new ArrayList<>(repositoryForecasts.findAll());
    }
}