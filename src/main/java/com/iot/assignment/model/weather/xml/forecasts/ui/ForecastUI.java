package com.iot.assignment.model.weather.xml.forecasts.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ForecastUI {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	private String date;
	@OneToOne(targetEntity = ForecastDayUI.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonProperty("day")
	private ForecastDayUI forecastDay;
	@OneToOne(targetEntity = ForecastNightUI.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonProperty("night")
	private ForecastNightUI forecastNight;
}
