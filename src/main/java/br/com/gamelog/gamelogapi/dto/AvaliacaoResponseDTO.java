package br.com.gamelog.gamelogapi.dto;


import br.com.gamelog.gamelogapi.model.Avaliacao;

import java.time.LocalDateTime;
import java.util.UUID;

public record AvaliacaoResponseDTO(
        UUID id,
        int nota,
        String textoReview,
        LocalDateTime dataCriacao,
        UsuarioResponseDTO usuario, // DTO aninhado!
        JogoResponseDTO jogo         // DTO aninhado!
) {

    // Construtor de conveniência para mapeamento fácil
    public AvaliacaoResponseDTO(Avaliacao avaliacao) {
        this(
                avaliacao.getId(),
                avaliacao.getNota(),
                avaliacao.getTextoReview(),
                avaliacao.getDataCriacao(),
                new UsuarioResponseDTO(avaliacao.getUsuario()), // Mapeia entidade para DTO
                new JogoResponseDTO(avaliacao.getJogo())       // Mapeia entidade para DTO
        );
    }
}