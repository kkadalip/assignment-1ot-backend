package com.iot.assignment.model.weather.xml.forecasts.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ForecastUI {
	@Id
	@GeneratedValue
	private Long id;

	private String date;
	@OneToOne(targetEntity = ForecastDayUI.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ForecastDayUI forecastDay;
	@OneToOne(targetEntity = ForecastNightUI.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ForecastNightUI forecastNight;
}
