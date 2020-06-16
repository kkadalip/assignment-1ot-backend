package com.iot.assignment.model.weather.xml.observations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ObservationUnits {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	private String visibility;
	private String airPressure;
	private String relativeHumidity;
	private String airTemperature;
	private String windDirection;
	private String windSpeed;
	private String waterLevel;
	private String waterTemperature;
}
