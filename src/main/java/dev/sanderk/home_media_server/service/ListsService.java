package dev.sanderk.home_media_server.service;


import dev.sanderk.home_media_server.dto.MainScreenListDTO;
import dev.sanderk.home_media_server.dto.MoviesCardDTO;
import dev.sanderk.home_media_server.dto.SeriesCardDTO;

import java.util.List;

public interface ListsService {
    /**
     * Returns the full list of all series.
     * Sends back series name, thumbnail url and rating. This is all the data we want for series screen list for now.
     *
     * @return seriesCardListDTO
     */
    List<SeriesCardDTO> seriesCardListDTO(int page, int size);

    /**
     * Returns the full list of all movies
     * Sends back movies name, thumbnail url and rating. This is all the data we want for movie screen list for now.
     *
     * @return MoviesListDTO
     */
    List<MoviesCardDTO> moviesCardListDTO(int page, int size);

    /**
     * Returns list of 6,8 or 10 randomized movies or series from database.
     * maximumCount is coming from configuration.
     * Validation checks if number from configuration is 6,8 or 10.
     *
     *
     * @return MainScreenListDTO
     * @Param int maximumCount
     */
    List<MainScreenListDTO> mainScreenListDTO();

    /**
     * Series that weren't finished. Data comes from SeriesWatchProgress entity
     *
     * @return SeriesListDTO
     */
    //List<SeriesListDTO> listForContinuingSeries();

    /**
     * Movies that weren't finished. Data comes from MovieWatchProgress entity
     *
     * @return MoviesListDTO
     */
    //List<MoviesListDTO> listForContinuingMovies();

    // Saved/Liked list of movies and series. Also on same screen.  One is up and other is down.




}
