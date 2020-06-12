package com.iot.assignment.service;

import com.iot.assignment.WeatherProperties;
import com.iot.assignment.interfaces.DownloadI;
import com.iot.assignment.model.weather.xml.ConversionUtil;
import com.iot.assignment.model.weather.xml.ObservationsDTO;
import com.iot.assignment.model.weather.xml.ObservationsUI;
import com.iot.assignment.model.weather.xml.StationDTO;
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
import java.net.MalformedURLException;
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

    public ObservationsDTO downloadObservationsDTO(String fileName) {
        log.info("starting download");
        ObservationsDTO observations = null;
        try {
            if (weatherProperties.isDownloadDevmodeOfflineSample()) {
                String pathToFile = "src/main/resources/static/observations_offline_development.xml";
                log.info("offline download url is " + pathToFile);
                observations = (ObservationsDTO) getUnmarshaller().unmarshal(new File(pathToFile));
            } else {
                String downloadUrl = weatherProperties.getDownloadUrl();
                log.info("online download url is " + downloadUrl);
                observations = downloadToDTO(downloadUrl);
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

    private Unmarshaller getUnmarshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ObservationsDTO.class);
        return jaxbContext.createUnmarshaller();
    }

    private ObservationsDTO downloadToDTO(String downloadURL) throws IOException, JAXBException {
        URL url = new URL(downloadURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        initHttpConnection(conn);
        conn.connect();
        BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
        ObservationsDTO result = (ObservationsDTO) getUnmarshaller().unmarshal(in);
        in.close();
        conn.disconnect();
        return result;
    }

    private void initHttpConnection(HttpURLConnection c) throws ProtocolException {
        c.setRequestMethod("GET");
        c.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
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
