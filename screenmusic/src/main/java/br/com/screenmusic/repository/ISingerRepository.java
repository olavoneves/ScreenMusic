package br.com.screenmusic.repository;

import br.com.screenmusic.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISingerRepository extends JpaRepository<Singer, Long> {
    Optional<Singer> findByNameContainingIgnoreCase(String cantor);
}
