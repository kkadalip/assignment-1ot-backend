package com.iot.assignment.model.weather.display;

import com.iot.assignment.model.weather.xml.observations.ui.ObservationsUI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin //NOSONAR
@Repository
//@RepositoryRestResource(path = "observations", collectionResourceRel = "observations") // Creates /api/observations
public interface RepositoryObservations extends JpaRepository<ObservationsUI, Long> {

}
