package br.com.gamelog.gamelogapi.dto;


import br.com.gamelog.gamelogapi.model.Jogo;
import java.time.LocalDate;
import java.util.UUID;

public record JogoResponseDTO(
        UUID id,
        String titulo,
        String genero,
        LocalDate dataLancamento
) {
    // Construtor de conveniência
    public JogoResponseDTO(Jogo jogo) {
        this(jogo.getId(), jogo.getTitulo(), jogo.getGenero(), jogo.getDataLancamento());
    }
}