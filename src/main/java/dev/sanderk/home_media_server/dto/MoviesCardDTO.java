package dev.sanderk.home_media_server.dto;
import lombok.Data;

@Data
public class MoviesCardDTO {
    private String movie_name;
    private double rating;
    private String thumbnail_url;

    public MoviesCardDTO() {
    }

    public MoviesCardDTO(String movie_name, double rating, String thumbnail_url) {
        this.movie_name = movie_name;
        this.rating = rating;
        this.thumbnail_url = thumbnail_url;
    }
}
