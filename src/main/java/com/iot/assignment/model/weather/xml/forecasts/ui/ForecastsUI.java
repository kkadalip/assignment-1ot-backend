package com.iot.assignment.model.weather.xml.forecasts.ui;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ForecastsUI {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(targetEntity = ForecastUI.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(name="forecasts")
    private List<ForecastUI> forecastUIs;
}