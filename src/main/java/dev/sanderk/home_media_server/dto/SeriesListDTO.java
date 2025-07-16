package dev.sanderk.home_media_server.dto;

import lombok.Data;

@Data
public class SeriesListDTO {

    private String series_name;
    private String thumbnail_url;
    private double rating;

    public SeriesListDTO() {
    }

    public SeriesListDTO(String series_name, String thumbnail_url, double rating) {
        this.series_name = series_name;
        this.thumbnail_url = thumbnail_url;
        this.rating = rating;
    }
}
