package com.iot.assignment.model.weather.display;

import com.iot.assignment.model.weather.xml.forecasts.ui.ForecastsUI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin //NOSONAR
@Repository
@RepositoryRestResource(path = "forecasts", collectionResourceRel = "forecasts")
public interface RepositoryForecasts extends JpaRepository<ForecastsUI, Long> {

}
