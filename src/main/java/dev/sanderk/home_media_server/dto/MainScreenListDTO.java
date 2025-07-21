package dev.sanderk.home_media_server.dto;

import lombok.Data;

import java.util.List;

@Data
public class MainScreenListDTO {
    private List<MoviesCardDTO> moviesCardDTO;
    private List<SeriesCardDTO> seriesCardDTO;

    public MainScreenListDTO() {
    }

    public MainScreenListDTO(List<MoviesCardDTO> moviesCardDTO, List<SeriesCardDTO> seriesCardDTO) {
        this.moviesCardDTO = moviesCardDTO;
        this.seriesCardDTO = seriesCardDTO;
    }
}
