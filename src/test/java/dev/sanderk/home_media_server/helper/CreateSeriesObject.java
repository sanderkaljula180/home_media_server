package dev.sanderk.home_media_server.helper;

import dev.sanderk.home_media_server.model.Series;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CreateSeriesObject {
    public Series newSeriesForTest() {
        Series series = new Series();
        series.setSeries_name("seriesName");
        series.setSeries_description("seriesDescription");
        series.setRating(5.2);
        series.setThumbnail_url("https://example.com/image.jpg");
        series.setRelease_date("10.05.2005");
        series.setSeasons_number(3);
        series.setEpisodes_number(30);
        series.setStudios(CreateStudioObject.newStudioSetForTest());
        series.setGenres(CreateGenreObject.newGenreListForTest());
        series.setEpisodes(CreateEpisodeObject.newEpisodeListForTest());

        return series;
    }

    public List<Series> newListOfSeriesForTest() {
        Series series = newSeriesForTest();
        Series series1 = newSeriesForTest();

        return new ArrayList<>(List.of(series, series1));
    }
}
