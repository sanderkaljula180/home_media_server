package dev.sanderk.home_media_server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "movie_videos")
public class MovieVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String format;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String resolution;

    @OneToOne(mappedBy = "movieVideo")
    private Movie movie;

    @OneToMany(mappedBy = "video")
    private List<MovieWatchProgress> watchProgresses;
}
