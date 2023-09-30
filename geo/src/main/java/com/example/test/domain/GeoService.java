package com.example.test.domain;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GeoService {

    private final GeoRepository geoRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional(readOnly = true)
    public List<GeoResponse> getGeos() {
        List<GeoDto> geos = geoRepository.findGeos();
        return geos.stream()
                .map(GeoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<GeoResponse> getGeosV2() {
        List<Geo> geos = geoRepository.findAll();
        return geos.stream()
                .map(GeoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public double getDistance(BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2) {
        try {
            Point point1 =
                    geometryFactory.createPoint(new Coordinate(x1.doubleValue(), y1.doubleValue()));
            point1.setSRID(4326);
            Point point2 =
                    geometryFactory.createPoint(new Coordinate(x2.doubleValue(), y2.doubleValue()));
            point2.setSRID(4326);
            CoordinateReferenceSystem crs = CRS.decode("EPSG:4326");
            return JTS.orthodromicDistance(point1.getCoordinate(), point2.getCoordinate(), crs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException();
        }
    }

    @Transactional
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
