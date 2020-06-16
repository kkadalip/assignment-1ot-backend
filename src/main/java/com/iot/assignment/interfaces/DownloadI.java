package com.iot.assignment.interfaces;

import com.iot.assignment.model.weather.xml.forecasts.dto.ForecastsDTO;
import com.iot.assignment.model.weather.xml.forecasts.ui.ForecastsUI;
import com.iot.assignment.model.weather.xml.observations.dto.ObservationsDTO;
import com.iot.assignment.model.weather.xml.observations.ui.ObservationsUI;

public interface DownloadI {
	ObservationsDTO downloadObservationsDTO();

	ObservationsUI downloadObservationsUI();

	ForecastsDTO downloadForecastsDTO();

	ForecastsUI downloadForecastsUI();
}
