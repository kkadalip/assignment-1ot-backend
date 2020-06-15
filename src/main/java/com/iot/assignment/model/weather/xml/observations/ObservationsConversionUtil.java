package com.iot.assignment.model.weather.xml.observations;

import com.iot.assignment.enums.TempEnum;
import com.iot.assignment.enums.UnitEnum;
import com.iot.assignment.model.weather.xml.observations.dto.ObservationsDTO;
import com.iot.assignment.model.weather.xml.observations.dto.StationDTO;
import com.iot.assignment.model.weather.xml.observations.ui.ObservationsUI;
import com.iot.assignment.model.weather.xml.observations.ui.StationUI;
import com.iot.assignment.model.weather.xml.statistics.ObservationStats;
import com.iot.assignment.model.weather.xml.statistics.Stats;
import com.iot.assignment.util.UnitUtil;
import com.iot.assignment.util.WindChillUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ObservationsConversionUtil {
    private ObservationsConversionUtil() {
        throw new IllegalStateException("Utility class!");
    }

    public static ObservationsUI convertDTOtoUI(ObservationsDTO dto) {
        if (dto == null) {
            log.error("convertDTOtoUI observationsDTO o is null!");
            return null;
        }
        ObservationsUI obsUI = new ObservationsUI();
        log.info("setting timestamp to " + dto.getTimestamp());
        obsUI.setTimestamp(dto.getTimestamp());
        List<StationUI> stationUIs = convertStationDTOtoUI(dto.getStations());
        obsUI.setStations(stationUIs);
        obsUI.setUnits(getObservationUnits());
        try {
            obsUI.setStatistics(calculateAndSetAverages(stationUIs));
        } catch (Exception ex) {
            log.error("Calculating and setting statistics failed", ex);
        }
        return obsUI;
    }

    private static ObservationUnits getObservationUnits() {
        return ObservationUnits.builder()
                .visibility(UnitEnum.KM.getSymbol())
                .airPressure(UnitEnum.HPA.getSymbol())
                .relativeHumidity(UnitEnum.PERCENTAGE.getSymbol())
                .airTemperature(TempEnum.C.getSymbol())
                .windDirection(UnitEnum.ANGLE.getSymbol())
                .windSpeed(UnitEnum.MS.getSymbol())
                .waterLevel(UnitEnum.MM.getSymbol())
                .waterTemperature(TempEnum.C.getSymbol())
                .build();
    }

    private static List<StationUI> convertStationDTOtoUI(List<StationDTO> stationDTOs) {
        List<StationUI> stationUIs = new ArrayList<>();
        for (StationDTO sDTO : stationDTOs) {
            StationUI sUI = StationUI.builder()
                    .name(sDTO.getName())
                    .wmoCode(sDTO.getWmoCode())
                    .longitude(sDTO.getLongitude())
                    .latitude(sDTO.getLatitude())
                    .phenomenon(sDTO.getPhenomenon())
                    .visibility(sDTO.getVisibility())
                    .precipitations(sDTO.getPrecipitations())
                    .airPressure(sDTO.getAirPressure())
                    .relativeHumidity(sDTO.getRelativeHumidity())
                    .airTemperature(sDTO.getAirTemperature())
                    .windDirection(sDTO.getWindDirection())
                    .windSpeed(sDTO.getWindSpeed())
                    .windSpeedMax(sDTO.getWindSpeedMax())
                    .waterLevel(sDTO.getWaterLevel())
                    .waterLevelEh2000(sDTO.getWaterLevelEh2000())
                    .waterTemperature(sDTO.getWaterTemperature())
                    .uvIndex(sDTO.getUvIndex())
                    .windChillC(WindChillUtil.calcWindChillInC(sDTO.getAirTemperature(), sDTO.getWindSpeed()))
                    .windChillF(WindChillUtil.calcWindChillInF(sDTO.getAirTemperature(), sDTO.getWindSpeed()))
                    .windChillMaxC(WindChillUtil.calcWindChillInC(sDTO.getAirTemperature(), sDTO.getWindSpeedMax()))
                    .windChillMaxF(WindChillUtil.calcWindChillInF(sDTO.getAirTemperature(), sDTO.getWindSpeedMax()))
                    .build();
            stationUIs.add(sUI);
        }
        return stationUIs;
    }

    private static ObservationStats calculateAndSetAverages(List<StationUI> stationUIs) {
        List<Double> visibilities = new ArrayList<>();
        List<Double> uvIndexes = new ArrayList<>();
        List<Double> airPressures = new ArrayList<>();
        List<Double> humidities = new ArrayList<>();
        List<Double> airtemps = new ArrayList<>();
        List<Double> windDirections = new ArrayList<>();
        List<Double> windSpeeds = new ArrayList<>();
        List<Double> windSpeedMaxs = new ArrayList<>();
        List<Double> waterLevels = new ArrayList<>();
        List<Double> waterLevelEH2000s = new ArrayList<>();
        List<Double> waterTemperatures = new ArrayList<>();
        List<Double> windChillCs = new ArrayList<>();
        List<Double> windChillMaxCs = new ArrayList<>();
        List<Double> windChillFs = new ArrayList<>();
        List<Double> windChillMaxFs = new ArrayList<>();
        for (StationUI s : stationUIs) {
            add(visibilities, s.getVisibility());
            add(uvIndexes, s.getUvIndex());
            add(airPressures, s.getAirPressure());
            add(humidities, s.getRelativeHumidity());
            add(airtemps, s.getAirTemperature());
            add(windDirections, s.getWindDirection());
            add(windSpeeds, s.getWindSpeed());
            add(windSpeedMaxs, s.getWindSpeedMax());
            add(waterLevels, s.getWaterLevel());
            add(waterLevelEH2000s, s.getWaterLevelEh2000());
            add(waterTemperatures, s.getWaterTemperature());
            add(windChillCs, s.getWindChillC());
            add(windChillMaxCs, s.getWindChillMaxC());
            add(windChillFs, s.getWindChillF());
            add(windChillMaxFs, s.getWindChillMaxF());
        }
        return ObservationStats.builder()
                .visibility(getStatsD(visibilities))
                .uvIndex(getStatsD(uvIndexes))
                .airPressure(getStatsD(airPressures))
                .humidity(getStatsD(humidities))
                .airTemperature(getStatsD(airtemps))
                .windDirection(getStatsD(windDirections))
                .windSpeed(getStatsD(windSpeeds))
                .windSpeedMax(getStatsD(windSpeedMaxs))
                .waterLevel(getStatsD(waterLevels))
                .waterLevelEH2000(getStatsD(waterLevelEH2000s))
                .waterTemperature(getStatsD(waterTemperatures))
                .windChillC(getStatsD(windChillCs))
                .windChillMaxC(getStatsD(windChillMaxCs))
                .windChillF(getStatsD(windChillFs))
                .windChillMaxF(getStatsD(windChillMaxFs))
                .build();
    }

    private static void add(List<Double> list, Double value) {
        if (value == null) {
            return;
        }
        list.add(value);
    }

    private static Stats getStatsD(List<Double> data) {
        return data != null ? convertToStatsD(getSummaryStatsD(data)) : null;
    }

    private static DoubleSummaryStatistics getSummaryStatsD(List<Double> data) {
        if (data == null) {
            return null;
        }
        try {
            return data.stream().filter(Objects::nonNull).mapToDouble(x -> x).summaryStatistics();
        } catch (NullPointerException ex) {
            log.error("getSummaryStatsD NullPointerException", ex);
        }
        return null;
    }

    private static Stats convertToStatsD(DoubleSummaryStatistics d) {
        return d != null ? Stats.builder().min(d.getMin()).max(d.getMax()).average(UnitUtil.round(d.getAverage())).count(d.getCount()).build() : null;
    }
}