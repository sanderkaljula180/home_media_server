package dev.sanderk.home_media_server.controller;

import dev.sanderk.home_media_server.dto.MainScreenSeriesListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/lists")
public class ListController {
    @GetMapping("/series")
    public ResponseEntity<List<MainScreenSeriesListDTO>> getAllSeries() {
        return null;
    }

}
