package com.iot.assignment.model.weather.xml.forecasts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
class ForecastUnits {
	@Id
	@GeneratedValue
	private Long id;

//	private String visibility;
//	private String airPressure;
//	private String relativeHumidity;
//	private String airTemperature;
//	private String windDirection;
//	private String windSpeed;
//	private String waterLevel;
//	private String waterTemperature;
}
