package com.example.test.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Getter
@Entity
@Table(name = "geo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Geo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "coordinates")
    private Point coordinates;

    @Builder
    private Geo(String name, Point coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public BigDecimal getX() {
        //Point 로부터 뽑은 X의 값은 경도가 된다.
        return BigDecimal.valueOf(coordinates.getX());
    }

    public BigDecimal getY() {
        //Point 로부터 뽑은 Y의 값은 위도가 된다.
        return BigDecimal.valueOf(coordinates.getY());
    }
}
