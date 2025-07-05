package dev.sanderk.home_media_server.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String episode_description;

    @Column(nullable = false)
    private int episode_number;

    @Column(nullable = false)
    private int season_number;

    @Column(nullable = false)
    private String release_date;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String thumbnail_url;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

}
