package com.iot.assignment.model.weather.xml.forecasts.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "forecast")
public class ForecastDTO {
    @XmlElement(name = "night")
    private ForecastNightDTO forecastNight;

    @XmlElement(name = "day")
    private ForecastDayDTO forecastDay;

    @XmlAttribute(name = "date")
    private String date;
}
