package dev.sanderk.home_media_server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String series_name;

    @Column(nullable = false)
    private String series_description;

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false)
    private String thumbnail_url;

    @Column(nullable = false)
    private String release_date;

    @Column(nullable = false)
    private int seasons_number;

    @Column(nullable = false)
    private int episodes_number;

    @ManyToMany
    @JoinTable(name = "series_studio",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id"))
    private Set<Studio> studios;

    @ManyToMany
    @JoinTable(name = "series_genre",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Episode> episodes;

}
