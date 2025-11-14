package br.com.gamelog.gamelogapi.service.impl;


import br.com.gamelog.gamelogapi.dto.AvaliacaoRequestDTO;
import br.com.gamelog.gamelogapi.dto.AvaliacaoResponseDTO;
import br.com.gamelog.gamelogapi.exception.RecursoNaoEncontradoException;
import br.com.gamelog.gamelogapi.model.Avaliacao;
import br.com.gamelog.gamelogapi.model.Jogo;
import br.com.gamelog.gamelogapi.model.Usuario;
import br.com.gamelog.gamelogapi.repository.AvaliacaoRepository;
import br.com.gamelog.gamelogapi.repository.JogoRepository;
import br.com.gamelog.gamelogapi.repository.UsuarioRepository;
import br.com.gamelog.gamelogapi.service.AvaliacaoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JogoRepository jogoRepository;

    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository,UsuarioRepository usuarioRepository,JogoRepository jogoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.jogoRepository = jogoRepository;
    }

    @Override
    public AvaliacaoResponseDTO criarAvaliacao(AvaliacaoRequestDTO dto) {
        // Lógica de Negócio: Buscar as entidades relacionadas
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        Jogo jogo = jogoRepository.findById(dto.jogoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Jogo não encontrado"));

        // (Opcional) Lógica extra: verificar se o usuário já avaliou este jogo
        // ...

        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setNota(dto.nota());
        novaAvaliacao.setTextoReview(dto.textoReview());
        novaAvaliacao.setUsuario(usuario); // Linka o usuário
        novaAvaliacao.setJogo(jogo);       // Linka o jogo

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(novaAvaliacao);

        // Retorna o DTO de resposta (que aninha os DTOs de usuário e jogo)
        return new AvaliacaoResponseDTO(avaliacaoSalva);
    }

    @Override
    public List<AvaliacaoResponseDTO> listarAvaliacoesPorJogo(UUID jogoId) {
        return avaliacaoRepository.findByJogoId(jogoId).stream()
                .map(AvaliacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<AvaliacaoResponseDTO> listarAvaliacoesPorUsuario(UUID usuarioId) {
        return avaliacaoRepository.findByUsuarioId(usuarioId).stream()
                .map(AvaliacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarAvaliacao(UUID id) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Avaliação não encontrada");
        }
        avaliacaoRepository.deleteById(id);
    }
}