package br.com.gamelog.gamelogapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AvaliacaoRequestDTO(
        @NotNull
        @Min(0) @Max(5)
        int nota,

        String textoReview,

        @NotNull
        UUID usuarioId,

        @NotNull
        UUID jogoId
) {
}