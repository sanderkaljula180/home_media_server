package dev.sanderk.home_media_server.helper;

import dev.sanderk.home_media_server.model.Studio;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UtilityClass
public class CreateStudioObject {
    public Set<Studio> newStudioSetForTest() {
        Studio studio1 = new Studio();
        studio1.setName("Hollywood");
        Studio studio2 = new Studio();
        studio2.setName("Bollywood");

        return new HashSet<>(List.of(studio1, studio2));
    }
}
