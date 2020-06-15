package com.iot.assignment.service;

import com.iot.assignment.interfaces.DbI;
import com.iot.assignment.interfaces.DownloadI;
import com.iot.assignment.model.weather.display.RepositoryForecasts;
import com.iot.assignment.model.weather.display.RepositoryObservations;
import com.iot.assignment.model.weather.xml.forecasts.ui.ForecastsUI;
import com.iot.assignment.model.weather.xml.observations.ui.ObservationsUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DbService implements DbI {
    private final DownloadI downloadI;
    private final RepositoryObservations repositoryObservations;
    private final RepositoryForecasts repositoryForecasts;

    @Autowired
    public DbService(RepositoryObservations repositoryObservations,
                     RepositoryForecasts repositoryForecasts, DownloadI downloadI) {
        this.repositoryObservations = repositoryObservations;
        this.repositoryForecasts = repositoryForecasts;
        this.downloadI = downloadI;
    }

    @Override
    public void updateObservations() {
        log.info("starting updateObservations");
        ObservationsUI ui = downloadI.downloadObservationsUI();
        if (ui == null) {
            log.error("downloader observations UI is null, can not save");
            return;
        }
        this.repositoryObservations.deleteAll();
        this.repositoryObservations.save(ui);
        log.info("finished updateObservations");
    }

    @Override
    public void updateForecasts() {
        log.info("starting updateForecasts");
        ForecastsUI ui = downloadI.downloadForecastsUI();
        if (ui == null) {
            log.error("downloader forecasts UI is null, can not save");
            return;
        }
        this.repositoryForecasts.deleteAll();
        this.repositoryForecasts.save(ui);
        log.info("finished updateForecasts");
    }
}
