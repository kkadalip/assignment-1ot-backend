package com.iot.assignment.model.weather.xml.forecasts.ui;

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
public class PlaceUI {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String phenomenon;
    private String tempMin;
}