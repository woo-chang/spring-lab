package com.example.test.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GeoRepository extends JpaRepository<Geo, Long> {

    /*
    조회 시 ST_X의 값은 기존 x의 경도가 아닌 위도가 된다.
    조회 시 ST_Y의 값은 기존 y의 위도가 아닌 경도가 된다.
     */
    @Query(
            nativeQuery = true,
            value = """
                        SELECT g.id, g.name, ST_X(g.coordinates) AS latitude, ST_Y(g.coordinates) AS longitude
                        FROM geo g
                    """
    )
    List<GeoDto> findGeos();

    @Query(
            nativeQuery = true,
            value = """
                    SELECT g.id, g.name, ST_X(g.coordinates) AS latitude, ST_Y(g.coordinates) AS longitude
                    FROM geo g
                    WHERE 
                    """
    )
    List<GeoDto> searchGeos();
}
