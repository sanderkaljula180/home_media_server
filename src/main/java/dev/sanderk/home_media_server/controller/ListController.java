package dev.sanderk.home_media_server.controller;

import dev.sanderk.home_media_server.dto.MoviesCardDTO;
import dev.sanderk.home_media_server.dto.SeriesCardDTO;
import dev.sanderk.home_media_server.repository.SeriesRepository;
import dev.sanderk.home_media_server.service.ListsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // This one is going do get pathvariables for pageable series?page={pageNumber}&size={sizeNumber}
    @GetMapping("/series?page={pageNumber}&size={sizeNumber}")
    public ResponseEntity<List<SeriesCardDTO>> getAllSeries(@PathVariable int pageNumber, @PathVariable int sizeNumber) {
        log.info("Get series list for series view",
                kv("event", "GET_LIST_REQUEST")
        );

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(listsService.seriesCardListDTO(pageNumber, sizeNumber));
    }

    @GetMapping("/movies?page={pageNumber}&size={sizeNumber}")
    public ResponseEntity<List<MoviesCardDTO>> getAllMovies(@PathVariable int pageNumber, @PathVariable int sizeNumber) {
        log.info("Get movies list for movies view",
                kv("event", "GET_LIST_REQUEST")
        );

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(listsService.moviesCardListDTO(pageNumber, sizeNumber));
    }

}
