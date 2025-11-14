package br.com.gamelog.gamelogapi.service;

import br.com.gamelog.gamelogapi.dto.JogoRequestDTO;
import br.com.gamelog.gamelogapi.dto.JogoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface JogoService {

    JogoResponseDTO criarJogo(JogoRequestDTO dto);

    JogoResponseDTO atualizarJogo(UUID id, JogoRequestDTO dto);

    List<JogoResponseDTO> listarJogos(String genero);

    JogoResponseDTO buscarJogoPorId(UUID id);

    void deletarJogo(UUID id);
}