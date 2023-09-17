package com.example.test.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GeoService {

    private final GeoRepository geoRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    public List<GeoResponse> getGeos() {
        List<GeoDto> geos = geoRepository.findGeos();
        return geos.stream()
                .map(GeoResponse::from)
                .toList();
    }

    public List<GeoResponse> getGeosV2() {
        List<Geo> geos = geoRepository.findAll();
        return geos.stream()
                .map(GeoResponse::from)
                .toList();
    }

    public void createGeo(GeoRequest geoRequest) {
        //데이터 저장 시 x는 경도, y는 위도
        Point point =
                geometryFactory.createPoint(new Coordinate(geoRequest.x().doubleValue(), geoRequest.y().doubleValue()));
        point.setSRID(4326);
        Geo geo = Geo.builder()
                .name(geoRequest.name())
                .coordinates(point)
                .build();
        geoRepository.save(geo);
    }
}
