package com.iot.assignment.model.weather.xml.forecasts.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "place")
public class PlaceDTO {
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "phenomenon")
	private String phenomenon;
	@XmlElement(name = "tempmin")
	private String tempMin;
}
