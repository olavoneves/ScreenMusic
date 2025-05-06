package br.com.screenmusic.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cantores")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "singer")
    private List<Music> listMusic;

    public Singer() {

    }

    public Long getId() {
        return id;
    }

    public Singer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Music> getListMusic() {
        return listMusic;
    }

    public Singer setListMusic(List<Music> listMusic) {
        this.listMusic = listMusic;
        return this;
    }
}
