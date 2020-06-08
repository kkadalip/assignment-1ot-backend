package com.iot.assignment.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.iot.assignment.config.properties.WeatherProperties;
import com.iot.assignment.interfaces.DownloadI;
import com.iot.assignment.model.weather.xml.ConversionUtil;
import com.iot.assignment.model.weather.xml.ObservationsDTO;
import com.iot.assignment.model.weather.xml.ObservationsUI;
import com.iot.assignment.model.weather.xml.StationDTO;

@Slf4j
@Service
public class DownloadService implements DownloadI {
	private final WeatherProperties weatherProperties;

	@Autowired
	public DownloadService(WeatherProperties weatherProperties) {
		this.weatherProperties = weatherProperties;
	}

	public ObservationsDTO downloadObservationsDTO(String fileName) {
		String resultFileName = fileName != null ? fileName : "temp.xml";
		log.info("starting download");
		ObservationsDTO observations = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ObservationsDTO.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			if (weatherProperties.isDownloadDevmodeOfflineSample()) {
				String pathToFile = "src/main/resources/static/observations_offline_development.xml";
				log.info("offline download url is " + pathToFile);
				observations = (ObservationsDTO) jaxbUnmarshaller.unmarshal(new File(pathToFile));
			} else {
				String downloadUrl = weatherProperties.getDownloadUrl();
				log.info("online download url is " + downloadUrl);
				File targetFile = downloadToFile(downloadUrl, resultFileName);
				observations = (ObservationsDTO) jaxbUnmarshaller.unmarshal(targetFile);
			}
		} catch (JAXBException e) {
			log.error("jaxb failed", e);
		} catch (MalformedURLException e) {
			log.error("downloadURL failed", e);
		} catch (Exception ex) {
			log.error("getting observations failed", ex);
		}
		log.info("download finished");
		debug(observations);
		return observations;
	}

	private File downloadToFile(String downloadURL, String fileName) throws Exception { // TODO DO NOT SAVE TO FILE!
		URL url = new URL(downloadURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String targetPath = "src/main/resources/static/observations/" + fileName;
		File targetFile = new File(targetPath);
		initHttpConnection(conn);
		conn.connect();
		BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
		OutputStream out = FileUtils.openOutputStream(targetFile);
		IOUtils.copy(in, out);
		in.close();
		out.close();
		conn.disconnect();
		return targetFile;
	}

	private void initHttpConnection(HttpURLConnection c) throws ProtocolException {
		//c.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		c.setRequestMethod("GET");
		//c.setRequestProperty("Host", "www.ilmateenistus.ee");
		c.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		//c.setRequestProperty("Accept-Encoding", "gzip, deflate");
		//c.setRequestProperty("Accept-Language", "en-US,en;q=0.9,et;q=0.8");
		//c.setRequestProperty("DNT", "1");
		//c.setRequestProperty("Upgrade-Insecure-Requests", "1");
		c.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
	}

	@Override
	public ObservationsUI downloadObservationsUI(String fileName) {
		return ConversionUtil.convertDTOtoUI(downloadObservationsDTO(fileName));
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
}
