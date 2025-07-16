package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.dto.SeriesListDTO;
import dev.sanderk.home_media_server.repository.SeriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
public class ListsServiceImpl implements ListsService {

    private final SeriesRepository seriesRepository;

    public ListsServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<SeriesListDTO> seriesListDTO() {
        List<SeriesListDTO> seriesListDTO = seriesRepository.findAll()
                .stream()
                .map(series -> new SeriesListDTO(
                        series.getSeries_name(),
                        series.getThumbnail_url(),
                        series.getRating()
                ))
                .toList();
        log.info("Get series list for main screen",
                kv("event", "GET_LIST_SUCCESS")
        );
        return seriesListDTO;
    }

}
