package dev.sanderk.home_media_server.repository;

import dev.sanderk.home_media_server.model.Series;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.nio.file.Path;
import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    @Query(value = "SELECT * FROM series ORDER BY RAND()", nativeQuery = true)
    List<Series> findRandomSeries(Pageable pageable);

    @Query(value = "SELECT * FROM series ORDER BY id", nativeQuery = true)
    List<Series> findAllSeriesWithPageable(Pageable pageable);

    // SQL command for finding series episode video
    // THIS IS WRONG. IT SHOULD BE SERIES NOT MOVIE
    @Query(value = "SELECT m.movie_name, mv.path " +
            "FROM movies m JOIN movie_videos mv " +
            "ON m.movie_videos_id = mv.id " +
            "WHERE m.movie_name = :name", nativeQuery = true)
    Path findSeriesPath(@Param("name") String name);
}
