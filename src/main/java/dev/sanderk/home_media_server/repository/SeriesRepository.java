package dev.sanderk.home_media_server.repository;

import dev.sanderk.home_media_server.model.Series;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    @Query(value = "SELECT * FROM series ORDER BY RAND()", nativeQuery = true)
    List<Series> findRandomSeries(Pageable pageable);

    @Query(value = "SELECT * FROM series ORDER BY id", nativeQuery = true)
    List<Series> findAllSeriesWithPageable(Pageable pageable);
}
