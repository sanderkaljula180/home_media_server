package dev.sanderk.home_media_server.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "episode_videos")
public class EpisodeVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String format;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String resolution;

    @OneToOne(mappedBy = "episodeVideo")
    private Episode episode;

}
