package com.iot.assignment.model.weather.xml.Statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ObservationStats {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats visibility;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats uvIndex;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats airPressure;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats humidity;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats airTemperature;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats windDirection;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats windSpeed;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats windSpeedMax;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats waterLevel;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats waterLevelEH2000;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats waterTemperature;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats windChillC;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats windChillMaxC;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats windChillF;
    @OneToOne(targetEntity = Stats.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Stats windChillMaxF;
}
