package com.iot.assignment;

import com.iot.assignment.interfaces.DbI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class ScheduledTasks {
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

	private final DbI dbI;

	@Autowired
	public ScheduledTasks(DbI dbI) {
		this.dbI = dbI;
	}

	@Scheduled(fixedRate = 60000) // Every 1 minutes (1 minutes * 60000 ms in one minute)
	public void updateObservations() {
		log.info("updateObservations :: Fixed Rate Task :: Execution Time - {}", dtf.format(LocalDateTime.now()));
		dbI.updateObservations();
		dbI.updateForecasts();
	}
}
