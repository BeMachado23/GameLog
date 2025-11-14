package br.com.gamelog.gamelogapi.repository;


import br.com.gamelog.gamelogapi.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {

    // Métodos que serão úteis no futuro (mais critérios de consulta)
    List<Avaliacao> findByJogoId(UUID jogoId);
    List<Avaliacao> findByUsuarioId(UUID usuarioId);
}