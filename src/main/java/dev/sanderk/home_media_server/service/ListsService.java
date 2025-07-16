package dev.sanderk.home_media_server.service;


import dev.sanderk.home_media_server.dto.SeriesListDTO;

import java.util.List;

public interface ListsService {
    /**
     * Returns the full list of all series through.
     * Sends back series name, thumbnail url and rating. This is all the data we want for main screen list for now.
     *
     * @return SeriesListDTO
     */
    List<SeriesListDTO> seriesListDTO();

    // Movies list

    // Series list




}
