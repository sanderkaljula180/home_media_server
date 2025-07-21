package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.config.ConfigProperties;
import dev.sanderk.home_media_server.dto.MainScreenListDTO;
import dev.sanderk.home_media_server.dto.MoviesCardDTO;
import dev.sanderk.home_media_server.dto.SeriesCardDTO;
import dev.sanderk.home_media_server.mapper.MoviesMapper;
import dev.sanderk.home_media_server.mapper.SeriesMapper;
import dev.sanderk.home_media_server.repository.MovieRepository;
import dev.sanderk.home_media_server.repository.SeriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;


@Slf4j
@Service
public class ListsServiceImpl implements ListsService {

    private final SeriesRepository seriesRepository;
    private final MovieRepository movieRepository;
    private final ConfigProperties configProperties;
    private final SeriesMapper seriesMapper;
    private final MoviesMapper moviesMapper;

    public ListsServiceImpl(SeriesRepository seriesRepository, MovieRepository movieRepository, ConfigProperties configProperties, SeriesMapper seriesMapper, MoviesMapper moviesMapper) {
        this.seriesRepository = seriesRepository;
        this.movieRepository = movieRepository;
        this.configProperties = configProperties;
        this.seriesMapper = seriesMapper;
        this.moviesMapper = moviesMapper;
    }

    @Override
    public List<SeriesCardDTO> seriesListDTO() {
        List<SeriesCardDTO> seriesCardDTO = seriesRepository.findAll()
                .stream()
                .map(seriesMapper::seriesIntoCardDTO)
                .toList();
        log.info("Get series list for main screen",
                kv("event", "GET_LIST_SUCCESS")
        );
        return seriesCardDTO;
    }

    @Override
    public List<MainScreenListDTO> mainScreenListDTO() {

        int maximumCount = configProperties.getMaximumNumberOfItemsOnList();
        int numberOfSeriesOnTheList = maximumCount / 2;
        int numberOfMoviesOnTheList = maximumCount - numberOfSeriesOnTheList;

        List<SeriesCardDTO> listOfRandomSeries = seriesRepository.findRandomSeries(PageRequest.of(0, numberOfSeriesOnTheList))
                .stream()
                .map(seriesMapper::seriesIntoCardDTO)
                .toList();

        List<MoviesCardDTO> listOfRandomMovies = movieRepository.findRandomMovies(PageRequest.of(0, numberOfMoviesOnTheList))
                .stream()
                .map(moviesMapper::moviesIntoCardDTO)
                .toList();

        return List.of(new MainScreenListDTO(listOfRandomMovies, listOfRandomSeries));
    }
}
