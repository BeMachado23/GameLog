package br.com.gamelog.gamelogapi.repository;

import br.com.gamelog.gamelogapi.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JogoRepository extends JpaRepository<Jogo, UUID> {

    // método de consulta por campo
    List<Jogo> findByGeneroIgnoreCase(String genero);
}