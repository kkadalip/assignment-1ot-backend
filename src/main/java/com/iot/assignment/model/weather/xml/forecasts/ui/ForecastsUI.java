package com.iot.assignment.model.weather.xml.forecasts.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class ForecastsUI {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	@OneToMany(targetEntity = ForecastUI.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonProperty("forecasts")
	private List<ForecastUI> forecastUIs;
}
