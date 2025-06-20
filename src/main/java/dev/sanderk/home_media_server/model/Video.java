package dev.sanderk.home_media_server.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private int duration;

    private String description;


}
