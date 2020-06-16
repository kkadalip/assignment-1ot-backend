package com.iot.assignment.model.weather.xml.forecasts.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ForecastDayUI {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	private String phenomenon;
	private String tempMin;
	private String tempMax;
	private String text;
	@OneToMany(targetEntity = PlaceUI.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<PlaceUI> places;
	@OneToMany(targetEntity = WindUI.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<WindUI> winds;
	@Column(columnDefinition = "TEXT")
	private String sea;
	@Column(columnDefinition = "TEXT")
	private String peipsi;
}
