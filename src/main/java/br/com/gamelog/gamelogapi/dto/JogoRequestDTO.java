package br.com.gamelog.gamelogapi.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record JogoRequestDTO(
        @NotBlank String titulo,
        String genero,
        LocalDate dataLancamento
) {
}