package br.com.gamelog.gamelogapi.dto;


import br.com.gamelog.gamelogapi.model.Usuario;
import java.util.UUID;

// Note que NUNCA retornamos a senha!
public record UsuarioResponseDTO(UUID id, String nome, String email) {

    // Construtor de conveniência para mapear da Entidade para o DTO
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}