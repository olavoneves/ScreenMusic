package br.com.screenmusic.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cantores")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "artista", unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private Banda banda;
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Music> listMusic;

    public Singer() {

    }

    public Singer(String cantor, Banda banda) {
        this.name = cantor;
        this.banda = banda;
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

    public Banda getBanda() {
        return banda;
    }

    public Singer setBanda(Banda banda) {
        this.banda = banda;
        return this;
    }

    @Override
    public String toString() {
        return "Artista {name= '" + name + '\'' + ", estilo da banda= " + banda + ", musicas= " + listMusic + '}';
    }
}
