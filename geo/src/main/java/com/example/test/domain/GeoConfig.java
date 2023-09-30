package com.example.test.domain;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeoConfig {

    @PostConstruct
    public void init() {
        System.setProperty("org.geotools.referencing.forceXY", "true");
    }
}
