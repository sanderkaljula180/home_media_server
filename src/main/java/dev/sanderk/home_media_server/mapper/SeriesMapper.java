package dev.sanderk.home_media_server.mapper;

import dev.sanderk.home_media_server.dto.SeriesCardDTO;
import dev.sanderk.home_media_server.model.Series;
import org.springframework.stereotype.Component;

@Component
public class SeriesMapper {
    public SeriesCardDTO seriesIntoCardDTO(Series series) {
        return new SeriesCardDTO(
                series.getSeries_name(),
                series.getThumbnail_url(),
                series.getRating()
        );
    }
}
