package com.iot.assignment.model.weather.xml.forecasts.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "day")
public class ForecastDayDTO {
    @XmlElement(name = "phenomenon")
    private String phenomenon;
    @XmlElement(name = "tempmin")
    private String tempMin;
    @XmlElement(name = "tempmax")
    private String tempMax;
    @XmlElement(name = "text")
    private String text;
    @XmlElement(name = "place")
    private List<PlaceDTO> places;
    @XmlElement(name = "wind")
    private List<WindDTO> winds;
    @XmlElement(name = "sea")
    private String sea;
    @XmlElement(name = "peipsi")
    private String peipsi;
}