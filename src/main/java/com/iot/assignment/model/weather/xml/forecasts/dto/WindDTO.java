package com.iot.assignment.model.weather.xml.forecasts.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "wind")
public class WindDTO {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "direction")
    private String direction;
    @XmlElement(name = "speedmin")
    private String speedmin;
    @XmlElement(name = "speedmax")
    private String speedmax;
    @XmlElement(name = "gust")
    private String gust;
}