package com.iot.assignment.model.weather.display;

import com.iot.assignment.interfaces.DbI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DatabaseLoader implements CommandLineRunner {

    private final DbI dbI;

    @Autowired
    public DatabaseLoader(DbI dbI) {
        this.dbI = dbI;
    }

    @Override
    public void run(String... strings) {
        dbI.updateObservations();
    }
}
