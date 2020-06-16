package com.iot.assignment.model.weather.xml.observations.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "station")
public class StationDTO {
	@XmlElement
	private String name;
	@XmlElement(name = "wmocode")
	private String wmoCode;
	@XmlElement
	private Double longitude;
	@XmlElement
	private Double latitude;
	@XmlElement
	private String phenomenon;
	@XmlElement
	private Double visibility;
	@XmlElement
	private Double precipitations;
	@XmlElement(name = "airpressure")
	private Double airPressure;
	@XmlElement(name = "relativehumidity")
	private Double relativeHumidity;
	@XmlElement(name = "airtemperature")
	private Double airTemperature;
	@XmlElement(name = "winddirection")
	private Double windDirection;
	@XmlElement(name = "windspeed")
	private Double windSpeed;
	@XmlElement(name = "windspeedmax")
	private Double windSpeedMax;
	@XmlElement(name = "waterlevel")
	private Double waterLevel;
	@XmlElement(name = "waterlevel_eh2000")
	private Double waterLevelEh2000;
	@XmlElement(name = "watertemperature")
	private Double waterTemperature;
	@XmlElement(name = "uvindex")
	private Double uvIndex;
}
