package br.com.gamelog.gamelogapi.controller;


import br.com.gamelog.gamelogapi.dto.JogoRequestDTO;
import br.com.gamelog.gamelogapi.dto.JogoResponseDTO;
import br.com.gamelog.gamelogapi.service.JogoService; // Importe o Serviço
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    // Injetamos o SERVIÇO
    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    // Critério: Criação
    @PostMapping
    public ResponseEntity<JogoResponseDTO> criarJogo(@Valid @RequestBody JogoRequestDTO dto) {
        JogoResponseDTO jogoSalvo = jogoService.criarJogo(dto);
        URI location = URI.create("/jogos/" + jogoSalvo.id());
        return ResponseEntity.created(location).body(jogoSalvo);
    }

    // Critério: Atualização
    @PutMapping("/{id}")
    public ResponseEntity<JogoResponseDTO> atualizarJogo(@PathVariable UUID id, @Valid @RequestBody JogoRequestDTO dto) {
        JogoResponseDTO jogoAtualizado = jogoService.atualizarJogo(id, dto);
        return ResponseEntity.ok(jogoAtualizado);
    }

    // Critério: Listar Todos E por Campo
    @GetMapping
    public ResponseEntity<List<JogoResponseDTO>> listarJogos(@RequestParam(required = false) String genero) {
        return ResponseEntity.ok(jogoService.listarJogos(genero));
    }

    // Critério: Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<JogoResponseDTO> buscarJogoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(jogoService.buscarJogoPorId(id));
    }

    // Critério: Exclusão
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogo(@PathVariable UUID id) {
        jogoService.deletarJogo(id);
        return ResponseEntity.noContent().build();
    }
}