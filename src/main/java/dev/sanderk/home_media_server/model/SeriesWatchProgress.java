package dev.sanderk.home_media_server.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "series_watch_progress")
public class SeriesWatchProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "series_id")
    private Series series;

    @ManyToOne(optional = false)
    @JoinColumn(name = "episode_id")
    private Episode episode;

    private Long position;
}
