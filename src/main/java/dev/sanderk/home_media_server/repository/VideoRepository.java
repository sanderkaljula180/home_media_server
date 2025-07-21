package dev.sanderk.home_media_server.repository;

import dev.sanderk.home_media_server.model.EpisodeVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<EpisodeVideo, Long> {
}
