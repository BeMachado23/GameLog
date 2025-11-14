package br.com.gamelog.gamelogapi.service.impl; // Ajuste o pacote se o seu for diferente

import br.com.gamelog.gamelogapi.dto.UsuarioRequestDTO;
import br.com.gamelog.gamelogapi.dto.UsuarioResponseDTO;
import br.com.gamelog.gamelogapi.exception.RecursoNaoEncontradoException;
import br.com.gamelog.gamelogapi.exception.RegraNegocioException;
import br.com.gamelog.gamelogapi.model.Usuario;
import br.com.gamelog.gamelogapi.repository.UsuarioRepository;
import br.com.gamelog.gamelogapi.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder; // <-- IMPORT NOVO
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // <-- CAMPO NOVO

    // O construtor agora pede o PasswordEncoder
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder; // <-- INJEÇÃO NOVA
    }

    @Override
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new RegraNegocioException("Email já cadastrado");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setEmail(dto.email());

        // --- MUDANÇA CRÍTICA AQUI ---
        // Não salvamos mais a senha pura. Nós a CRIPTOGRAFAMOS.
        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        novoUsuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return new UsuarioResponseDTO(usuarioSalvo);
    }

    @Override
    public UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        usuarioRepository.findByEmail(dto.email()).ifPresent(usuarioExistente -> {
            if (!usuarioExistente.getId().equals(id)) {
                throw new RegraNegocioException("Email já cadastrado por outro usuário");
            }
        });

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        // --- MUDANÇA CRÍTICA AQUI (TAMBÉM NA ATUALIZAÇÃO) ---
        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuarioAtualizado);
    }

    // --- MÉTODOS RESTANTES (SEM MUDANÇAS) ---

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO buscarUsuarioPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        return new UsuarioResponseDTO(usuario);
    }

    @Override
    public void deletarUsuario(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}