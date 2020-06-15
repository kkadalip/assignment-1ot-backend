package com.iot.assignment;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotNull;

@Slf4j
@Data
@Configuration
@PropertySource("classpath:config/weather.properties")
public class WeatherProperties {

    @NotNull
    @Value("${download.url.observations}")
    private String downloadUrlObservations;

    @NotNull
    @Value("${download.url.forecasts}")
    private String downloadUrlForecasts;

    @Value("${download.url.observations.devmode-offline-sample}")
    private boolean downloadDevmodeOfflineSampleObservations;

    @Value("${download.url.observations.devmode-offline-sample.path}")
    private String downloadDevmodeOfflineSampleObservationsPath;

    @Value("${download.url.forecasts.devmode-offline-sample}")
    private boolean downloadDevmodeOfflineSampleForecasts;

    @Value("${download.url.forecasts.devmode-offline-sample.path}")
    private String downloadDevmodeOfflineSampleForecastsPath;
}
