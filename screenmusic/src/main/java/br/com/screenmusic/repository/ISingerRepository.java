package br.com.screenmusic.repository;

import br.com.screenmusic.model.Music;
import br.com.screenmusic.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISingerRepository extends JpaRepository<Singer, Long> {
    Optional<Singer> findByNameContainingIgnoreCase(String cantor);

    @Query("SELECT music FROM Singer cantor JOIN cantor.listMusic music WHERE cantor.name ILIKE %:cantor%")
    List<Music> buscarMusica(String cantor);
}
