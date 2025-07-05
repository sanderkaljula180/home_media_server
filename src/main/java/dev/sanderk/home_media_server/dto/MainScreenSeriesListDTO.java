package dev.sanderk.home_media_server.dto;

import lombok.Data;

@Data
public class MainScreenSeriesListDTO {

    private String series_name;
    private String thumbnail_url;
    private String rating;

    public MainScreenSeriesListDTO() {
    }

    public MainScreenSeriesListDTO(String series_name, String thumbnail_url, String rating) {
        this.series_name = series_name;
        this.thumbnail_url = thumbnail_url;
        this.rating = rating;
    }
}
