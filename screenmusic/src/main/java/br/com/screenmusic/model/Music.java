package br.com.screenmusic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    private Singer singer;

    public Music() {

    }

    public Long getId() {
        return id;
    }

    public Music setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Singer getSinger() {
        return singer;
    }

    public Music setSinger(Singer singer) {
        this.singer = singer;
        return this;
    }
}
