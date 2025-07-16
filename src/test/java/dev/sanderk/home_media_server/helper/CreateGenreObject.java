package dev.sanderk.home_media_server.helper;

import dev.sanderk.home_media_server.model.Genre;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UtilityClass
public class CreateGenreObject {
    public Set<Genre> newGenreListForTest() {
        Genre genre1 = new Genre();
        genre1.setTag("action");
        Genre genre2 = new Genre();
        genre2.setTag("comedy");

        return new HashSet<>(List.of(genre1, genre2));
    }
}
