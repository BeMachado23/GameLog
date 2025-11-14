package br.com.gamelog.gamelogapi.service;


import br.com.gamelog.gamelogapi.dto.AvaliacaoRequestDTO;
import br.com.gamelog.gamelogapi.dto.AvaliacaoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AvaliacaoService {

    AvaliacaoResponseDTO criarAvaliacao(AvaliacaoRequestDTO dto);

    List<AvaliacaoResponseDTO> listarAvaliacoesPorJogo(UUID jogoId);

    List<AvaliacaoResponseDTO> listarAvaliacoesPorUsuario(UUID usuarioId);

    void deletarAvaliacao(UUID id);

    // Poderíamos adicionar um PATCH para atualizar a nota/review, mas vamos focar no DELETE por enquanto.
}