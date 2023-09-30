package com.example.test.domain;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/geos")
@RestController
public class GeoController {

    private final GeoService geoService;

    @GetMapping
    public ResponseEntity<List<GeoResponse>> getGeos() {
        List<GeoResponse> response = geoService.getGeos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v2")
    public ResponseEntity<List<GeoResponse>> getGeosV2() {
        List<GeoResponse> response = geoService.getGeosV2();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/distance")
    public ResponseEntity<Double> getDistance(
            BigDecimal x1,
            BigDecimal y1,
            BigDecimal x2,
            BigDecimal y2
    ) {
        double distance = geoService.getDistance(x1, y1, x2, y2);
        return ResponseEntity.ok(distance);
    }

    @PostMapping
    public ResponseEntity<Void> createGeo(@RequestBody GeoRequest geoRequest) {
        geoService.createGeo(geoRequest);
        return ResponseEntity.ok().build();
    }
}
