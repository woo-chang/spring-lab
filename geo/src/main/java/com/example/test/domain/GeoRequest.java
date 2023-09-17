package com.example.test.domain;

import java.math.BigDecimal;

public record GeoRequest(
        String name,
        //경도
        BigDecimal x,
        //위도
        BigDecimal y
) {
}
