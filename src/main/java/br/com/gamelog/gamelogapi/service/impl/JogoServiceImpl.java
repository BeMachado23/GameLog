package br.com.gamelog.gamelogapi.service.impl;

import br.com.gamelog.gamelogapi.dto.JogoRequestDTO;
import br.com.gamelog.gamelogapi.dto.JogoResponseDTO;
import br.com.gamelog.gamelogapi.exception.RecursoNaoEncontradoException;
import br.com.gamelog.gamelogapi.model.Jogo;
import br.com.gamelog.gamelogapi.repository.JogoRepository;
//import br.com.gamelog.gamelogapi.service.JogoService;
import br.com.gamelog.gamelogapi.service.JogoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JogoServiceImpl implements JogoService {

    private final JogoRepository jogoRepository;

    public JogoServiceImpl(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    @Override
    public JogoResponseDTO criarJogo(JogoRequestDTO dto) {
        Jogo novoJogo = new Jogo();
        novoJogo.setTitulo(dto.titulo());
        novoJogo.setGenero(dto.genero());
        novoJogo.setDataLancamento(dto.dataLancamento());

        Jogo jogoSalvo = jogoRepository.save(novoJogo);
        return new JogoResponseDTO(jogoSalvo);
    }

    // Critério: Atualização de Registro
    @Override
    public JogoResponseDTO atualizarJogo(UUID id, JogoRequestDTO dto) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Jogo não encontrado"));

        jogo.setTitulo(dto.titulo());
        jogo.setGenero(dto.genero());
        jogo.setDataLancamento(dto.dataLancamento());

        Jogo jogoAtualizado = jogoRepository.save(jogo);
        return new JogoResponseDTO(jogoAtualizado);
    }

    @Override
    public List<JogoResponseDTO> listarJogos(String genero) {
        List<Jogo> jogos;
        if (genero != null && !genero.isBlank()) {
            jogos = jogoRepository.findByGeneroIgnoreCase(genero);
        } else {
            jogos = jogoRepository.findAll();
        }

        return jogos.stream()
                .map(JogoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public JogoResponseDTO buscarJogoPorId(UUID id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Jogo não encontrado"));
        return new JogoResponseDTO(jogo);
    }

    @Override
    public void deletarJogo(UUID id) {
        if (!jogoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Jogo não encontrado");
        }
        jogoRepository.deleteById(id);
    }
}