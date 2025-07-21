package dev.sanderk.home_media_server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String movie_name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private String thumbnail_url;

    @Column(nullable = false)
    private String release_date;

    @ManyToMany
    @JoinTable(name = "movie_studio",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id"))
    private Set<Studio> studios;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @OneToOne
    @JoinColumn(name = "movie_video_id")
    private MovieVideo movieVideo;

}
