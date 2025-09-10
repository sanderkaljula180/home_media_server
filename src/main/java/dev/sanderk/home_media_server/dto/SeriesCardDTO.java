package dev.sanderk.home_media_server.dto;

import lombok.Data;

@Data
public class SeriesCardDTO {

    private String series_name;
    private String thumbnail_url;
    private double rating;
    private String series_description;

    public SeriesCardDTO() {
    }

    public SeriesCardDTO(String series_name, String thumbnail_url, double rating, String series_description) {
        this.series_name = series_name;
        this.thumbnail_url = thumbnail_url;
        this.rating = rating;
        this.series_description = series_description;
    }
}
