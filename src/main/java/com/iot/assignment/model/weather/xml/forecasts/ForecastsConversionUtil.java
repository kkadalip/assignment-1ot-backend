package com.iot.assignment.model.weather.xml.forecasts;

import com.iot.assignment.model.weather.xml.forecasts.dto.*;
import com.iot.assignment.model.weather.xml.forecasts.ui.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ForecastsConversionUtil {
    private ForecastsConversionUtil() {
        throw new IllegalStateException("Utility class!");
    }

    public static ForecastsUI convertDTOtoUI(ForecastsDTO dto) {
        if (dto == null) {
            log.error("convertDTOtoUI forecastsDTO o is null!");
            return null;
        }
        ForecastsUI ui = new ForecastsUI();
        ui.setForecastUIs(convertForecastsDTOtoUI(dto.getForecasts()));
        return ui;
    }

    private static List<ForecastUI> convertForecastsDTOtoUI(List<ForecastDTO> forecastDTOs) {
        List<ForecastUI> results = new ArrayList<>();
        forecastDTOs.forEach(dto ->
                results.add(ForecastUI.builder()
                        .date(dto.getDate())
                        .forecastNight(convertForecastNightDTOtoUI(dto.getForecastNight()))
                        .forecastDay(convertForecastDayDTOtoUI(dto.getForecastDay()))
                        .build())
        );
        return results;
    }

    private static ForecastNightUI convertForecastNightDTOtoUI(ForecastNightDTO dto) {
        return ForecastNightUI.builder()
                .phenomenon(dto.getPhenomenon())
                .tempMin(dto.getTempMin())
                .tempMax(dto.getTempMax())
                .text(dto.getText())
                .places(convertPlacesDTOtoUI(dto.getPlaces()))
                .winds(convertWindsDTOtoUI(dto.getWinds()))
                .sea(dto.getSea())
                .peipsi(dto.getPeipsi())
                .build();
    }

    private static ForecastDayUI convertForecastDayDTOtoUI(ForecastDayDTO dto) {
        return ForecastDayUI.builder()
                .phenomenon(dto.getPhenomenon())
                .tempMin(dto.getTempMin())
                .tempMax(dto.getTempMax())
                .text(dto.getText())
                .places(convertPlacesDTOtoUI(dto.getPlaces()))
                .winds(convertWindsDTOtoUI(dto.getWinds()))
                .sea(dto.getSea())
                .peipsi(dto.getPeipsi())
                .build();
    }

    private static List<PlaceUI> convertPlacesDTOtoUI(List<PlaceDTO> places) {
        List<PlaceUI> results = new ArrayList<>();
        if (places == null) {
            return results;
        }
        places.forEach(dto ->
                results.add(PlaceUI.builder()
                        .name(dto.getName())
                        .phenomenon(dto.getPhenomenon())
                        .tempMin(dto.getTempMin())
                        .build())
        );
        return results;
    }

    private static List<WindUI> convertWindsDTOtoUI(List<WindDTO> winds) {
        List<WindUI> results = new ArrayList<>();
        if (winds == null) {
            return results;
        }
        winds.forEach(dto ->
                results.add(WindUI.builder()
                        .name(dto.getName())
                        .direction(dto.getDirection())
                        .speedmin(dto.getSpeedmin())
                        .speedmax(dto.getSpeedmax())
                        .gust(dto.getGust())
                        .build())
        );
        return results;
    }
}