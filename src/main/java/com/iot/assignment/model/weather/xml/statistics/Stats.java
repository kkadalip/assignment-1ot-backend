package com.iot.assignment.model.weather.xml.statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Stats {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	private Double min;
	private Double max;
	private Double average;
	private Long count;
}
