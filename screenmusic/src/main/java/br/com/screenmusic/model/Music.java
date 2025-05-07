package br.com.screenmusic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "musica", unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private StyleMusic estiloDaMusica;
    @ManyToOne
    private Singer singer;

    public Music() {

    }

    public Music(String musica, StyleMusic estiloMusica) {
        this.name = musica;
        this.estiloDaMusica = estiloMusica;
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

    public StyleMusic getEstiloDaMusica() {
        return estiloDaMusica;
    }

    public Music setEstiloDaMusica(StyleMusic estiloDaMusica) {
        this.estiloDaMusica = estiloDaMusica;
        return this;
    }

    @Override
    public String toString() {
        return  "Musica {name= '" + name + '\'' + ", estilo da musica= " + estiloDaMusica + ", artista= " + singer.getName() + '}';
    }
}
