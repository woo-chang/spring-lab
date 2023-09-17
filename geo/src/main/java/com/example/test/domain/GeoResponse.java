package com.example.test.domain;

import java.math.BigDecimal;

public record GeoResponse(
        Long id,
        String name,
        //경도
        BigDecimal x,
        //위도
        BigDecimal y
) {

    public static GeoResponse from(GeoDto geoDto) {
        return new GeoResponse(
                geoDto.getId(),
                geoDto.getName(),
                geoDto.getLongitude(),
                geoDto.getLatitude()
        );
    }

    public static GeoResponse from(Geo geo) {
        return new GeoResponse(
                geo.getId(),
                geo.getName(),
                geo.getX(),
                geo.getY()
        );
    }
}
