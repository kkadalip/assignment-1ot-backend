package com.iot.assignment.model.weather.display;

import com.iot.assignment.model.weather.xml.ObservationsUI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Repository
@RepositoryRestResource(path = "observations", collectionResourceRel = "observations")
public interface ObservationsRepository extends JpaRepository<ObservationsUI, Long> {

}
