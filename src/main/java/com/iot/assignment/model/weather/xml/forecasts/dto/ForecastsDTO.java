package com.iot.assignment.model.weather.xml.forecasts.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "forecasts")
public class ForecastsDTO {
	@XmlElement(name = "forecast")
	private List<ForecastDTO> forecasts;
}
