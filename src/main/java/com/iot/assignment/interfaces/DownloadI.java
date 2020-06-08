package com.iot.assignment.interfaces;

import com.iot.assignment.model.weather.xml.ObservationsDTO;
import com.iot.assignment.model.weather.xml.ObservationsUI;

public interface DownloadI {
    ObservationsDTO downloadObservationsDTO(String fileName);

    ObservationsUI downloadObservationsUI(String fileName);
}
