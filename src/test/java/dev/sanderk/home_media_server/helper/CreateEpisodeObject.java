package dev.sanderk.home_media_server.helper;

import dev.sanderk.home_media_server.model.Episode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CreateEpisodeObject {
    public List<Episode> newEpisodeListForTest() {
        Episode episode = new Episode();
        episode.setTitle("Pilot");
        episode.setEpisode_description("The first episode of the series");
        episode.setEpisode_number(1);
        episode.setSeason_number(1);
        episode.setRelease_date("05.05.2005");
        episode.setDuration("45:00");
        episode.setThumbnail_url("https://example.com/thumbnails/ep1.jpg");

        Episode episode2 = new Episode();
        episode2.setTitle("Pilot");
        episode2.setEpisode_description("The first episode of the series");
        episode2.setEpisode_number(1);
        episode2.setSeason_number(1);
        episode2.setRelease_date("05.05.2005");
        episode2.setDuration("45:00");
        episode2.setThumbnail_url("https://example.com/thumbnails/ep1.jpg");

        return new ArrayList<>(List.of(episode, episode2));
    }
}
