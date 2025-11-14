package br.com.gamelog.gamelogapi.repository;


import br.com.gamelog.gamelogapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    //igual ao findByEmail no AlunoRepository,
    //isso será crucial para evitar duplicados.
    Optional<Usuario> findByEmail(String email);
}