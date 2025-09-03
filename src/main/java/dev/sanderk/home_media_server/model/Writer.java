package dev.sanderk.home_media_server.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "writers")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String writer;

}
