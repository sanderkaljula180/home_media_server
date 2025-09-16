package dev.sanderk.home_media_server.controller;

import dev.sanderk.home_media_server.dto.MoviesCardDTO;
import dev.sanderk.home_media_server.dto.SeriesCardDTO;
import dev.sanderk.home_media_server.repository.SeriesRepository;
import dev.sanderk.home_media_server.service.ListsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<SeriesCardDTO>> getAllSeries(@RequestParam int pageNumber, @RequestParam int sizeNumber) {
        log.info("Get series list for series view",
                kv("event", "GET_LIST_REQUEST")
        );

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(listsService.seriesCardListDTO(pageNumber, sizeNumber));
    }

    // Validate page size and also pages sum. Set it as configuration
    @GetMapping("/movies")
    public ResponseEntity<Slice<MoviesCardDTO>> getAllMovies(@RequestParam int page, @RequestParam int size) {
        log.info("Get movies list for movies view",
                kv("event", "GET_LIST_REQUEST")
        );

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(listsService.moviesCardListDTO(page, size));
    }

}
