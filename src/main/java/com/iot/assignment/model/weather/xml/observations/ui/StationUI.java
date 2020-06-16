package com.iot.assignment.model.weather.xml.observations.ui;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class StationUI {
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String wmoCode;
	private Double longitude;
	private Double latitude;
	private String phenomenon;
	private Double visibility;
	private Double precipitations;
	private Double airPressure;
	private Double relativeHumidity;
	private Double airTemperature;
	private Double windDirection;
	private Double windSpeed;
	private Double windSpeedMax;
	private Double waterLevel;
	private Double waterLevelEh2000;
	private Double waterTemperature;
	private Double uvIndex;

	private Double windChillC;
	private Double windChillMaxC;
	private Double windChillF;
	private Double windChillMaxF;
}
