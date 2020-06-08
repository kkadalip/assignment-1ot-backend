package com.iot.assignment.config.properties;

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
    @Value("${download.url}")
    private String downloadUrl;

    @Value("${download.devmode.offline-sample}")
    private boolean downloadOfflineSampleInstead;

    public boolean isDownloadDevmodeOfflineSample() {
        if (downloadOfflineSampleInstead) {
            log.warn("DEV MODE ON! USING OFFLINE WEATHER XML SAMPLE INSTEAD");
        }
        return downloadOfflineSampleInstead;
    }
}
