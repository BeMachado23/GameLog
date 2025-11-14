package br.com.gamelog.gamelogapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String genero;

    @Column
    private LocalDate dataLancamento;

    @OneToMany(mappedBy = "jogo")
    private List<Avaliacao> avaliacoes;
}