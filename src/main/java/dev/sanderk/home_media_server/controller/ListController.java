package dev.sanderk.home_media_server.controller;

import dev.sanderk.home_media_server.dto.SeriesCardDTO;
import dev.sanderk.home_media_server.repository.SeriesRepository;
import dev.sanderk.home_media_server.service.ListsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RestController
@RequestMapping("/api/lists")
public class ListController {

    private final SeriesRepository seriesRepository;
    private final ListsServiceImpl listsService;

    public ListController(SeriesRepository seriesRepository, ListsServiceImpl listsService) {
        this.seriesRepository = seriesRepository;
        this.listsService = listsService;
    }

    @GetMapping("/series")
    public ResponseEntity<List<SeriesCardDTO>> getAllSeries() {
        log.info("Get series list for main screen",
                kv("event", "GET_LIST_REQUEST")
        );

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(listsService.seriesListDTO());
    }

}
