package com.iot.assignment.service;

import com.iot.assignment.WeatherProperties;
import com.iot.assignment.interfaces.DownloadI;
import com.iot.assignment.model.weather.xml.forecasts.dto.ForecastDTO;
import com.iot.assignment.model.weather.xml.forecasts.ForecastsConversionUtil;
import com.iot.assignment.model.weather.xml.forecasts.dto.ForecastsDTO;
import com.iot.assignment.model.weather.xml.forecasts.ui.ForecastsUI;
import com.iot.assignment.model.weather.xml.observations.ObservationsConversionUtil;
import com.iot.assignment.model.weather.xml.observations.dto.ObservationsDTO;
import com.iot.assignment.model.weather.xml.observations.ui.ObservationsUI;
import com.iot.assignment.model.weather.xml.observations.dto.StationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

@Slf4j
@Service
public class DownloadService implements DownloadI {
	private final WeatherProperties weatherProperties;

	@Autowired
	public DownloadService(WeatherProperties weatherProperties) {
		this.weatherProperties = weatherProperties;
	}

	public ObservationsDTO downloadObservationsDTO() {
		log.info("observations starting download");
		ObservationsDTO observations;
		if (weatherProperties.isDownloadDevmodeOfflineSampleObservations()) {
			String pathToFile = weatherProperties.getDownloadDevmodeOfflineSampleObservationsPath();
			log.warn("[DEVMODE] observations offline download url is " + pathToFile);
			observations = (ObservationsDTO) downloadFileToObjectObservationsDTO(pathToFile);
		} else {
			String downloadUrl = weatherProperties.getDownloadUrlObservations();
			log.info("[LIVEMODE] observations online download url is " + downloadUrl);
			observations = downloadObservationsToDTO(downloadUrl);
		}
		log.info("download finished");
		debug(observations);
		return observations;
	}

	public ForecastsDTO downloadForecastsDTO() {
		log.info("forecasts starting download");
		ForecastsDTO forecasts;
		if (weatherProperties.isDownloadDevmodeOfflineSampleForecasts()) {
			String pathToFile = weatherProperties.getDownloadDevmodeOfflineSampleForecastsPath();
			log.warn("[DEVMODE] forecasts offline download url is " + pathToFile);
			forecasts = (ForecastsDTO) downloadFileToObjectForecastsDTO(pathToFile);
		} else {
			String downloadUrl = weatherProperties.getDownloadUrlForecasts();
			log.warn("[LIVEMODE] forecasts online download url is " + downloadUrl);
			forecasts = downloadForecastsToDTO(downloadUrl);
		}
		debug(forecasts);
		log.info("download finished");
		return forecasts;
	}

	private Unmarshaller getUnmarshallerObservations() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ObservationsDTO.class);
		return jaxbContext.createUnmarshaller();
	}

	private Unmarshaller getUnmarshallerForecasts() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ForecastsDTO.class);
		return jaxbContext.createUnmarshaller();
	}

	private ObservationsDTO downloadObservationsToDTO(String downloadURL) {
		try {
			return (ObservationsDTO) downloadToObjectDTO(downloadURL, getUnmarshallerObservations());
		} catch (Exception e) {
			log.error("downloadObservationsToDTO failed", e);
		}
		return null;
	}

	private ForecastsDTO downloadForecastsToDTO(String downloadURL) {
		try {
			return (ForecastsDTO) downloadToObjectDTO(downloadURL, getUnmarshallerForecasts());
		} catch (Exception e) {
			log.error("downloadForecastsToDTO failed", e);
		}
		return null;
	}

	private Object downloadFileToObjectObservationsDTO(String pathToFile) {
		try {
			return getUnmarshallerObservations().unmarshal(new File(pathToFile));
		} catch (Exception e) {
			log.error("downloadFileToObjectObservationsDTO failed", e);
		}
		return null;
	}

	private Object downloadFileToObjectForecastsDTO(String pathToFile) {
		try {
			return getUnmarshallerForecasts().unmarshal(new File(pathToFile));
		} catch (Exception e) {
			log.error("downloadFileToObjectForecastsDTO failed", e);
		}
		return null;
	}

	private Object downloadToObjectDTO(String downloadURL, Unmarshaller unmarshaller) throws IOException, JAXBException {
		URL url = new URL(downloadURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		initHttpConnection(conn);
		conn.connect();
		BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
		Object result = unmarshaller.unmarshal(in);
		in.close();
		conn.disconnect();
		return result;
	}

	private void initHttpConnection(HttpURLConnection c) throws ProtocolException {
		c.setRequestMethod("GET");
		c.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		c.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
	}

	@Override
	public ObservationsUI downloadObservationsUI() {
		return ObservationsConversionUtil.convertDTOtoUI(downloadObservationsDTO());
	}

	@Override
	public ForecastsUI downloadForecastsUI() {
		return ForecastsConversionUtil.convertDTOtoUI(downloadForecastsDTO());
	}

	private void debug(ObservationsDTO observations) {
		if (observations != null) {
			List<StationDTO> displayedStations = observations.getStations();
			for (StationDTO station : displayedStations) {
				log.info(String.valueOf(station));
			}
		} else {
			log.error("observations is null");
		}
	}

	private void debug(ForecastsDTO forecasts) {
		if (forecasts != null) {
			List<ForecastDTO> displayedStations = forecasts.getForecasts();
			for (ForecastDTO forecast : displayedStations) {
				log.info(String.valueOf(forecast));
			}
		} else {
			log.error("forecasts is null");
		}
	}
}
