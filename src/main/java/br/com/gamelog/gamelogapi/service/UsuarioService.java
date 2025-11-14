package br.com.gamelog.gamelogapi.service;

import br.com.gamelog.gamelogapi.dto.UsuarioRequestDTO;
import br.com.gamelog.gamelogapi.dto.UsuarioResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {

    UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto);

    UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO dto);

    List<UsuarioResponseDTO> listarUsuarios();

    UsuarioResponseDTO buscarUsuarioPorId(UUID id);

    void deletarUsuario(UUID id);
}