package com.example.test.domain;

import java.math.BigDecimal;

public interface GeoDto {

    Long getId();

    String getName();

    //경도
    BigDecimal getLongitude();

    //위도
    BigDecimal getLatitude();
}
