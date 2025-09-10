package dev.sanderk.home_media_server.mapper;

import dev.sanderk.home_media_server.dto.MoviesCardDTO;
import dev.sanderk.home_media_server.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MoviesMapper {
    public MoviesCardDTO moviesIntoCardDTO(Movie movie) {
        return new MoviesCardDTO(
                movie.getMovie_name(),
                movie.getRating(),
                movie.getThumbnail_url(),
                movie.getDescription()
        );
    }
}
