package br.com.screenmusic.repository;

import br.com.screenmusic.model.Music;
import br.com.screenmusic.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMusicRepository extends JpaRepository<Music, Long> {

    @Query("SELECT c FROM Singer c WHERE c.name = :cantor")
    List<Singer> buscarCantor(String cantor);
}
