-- V1: Criação das tabelas iniciais (Usuarios, Jogos, Avaliacoes)

CREATE TABLE usuarios (
                          id UUID NOT NULL PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          senha VARCHAR(255) NOT NULL
);

CREATE TABLE jogos (
                       id UUID NOT NULL PRIMARY KEY,
                       titulo VARCHAR(255) NOT NULL,
                       genero VARCHAR(100),
                       data_lancamento DATE
);

CREATE TABLE avaliacoes (
                            id UUID NOT NULL PRIMARY KEY,
                            nota INT NOT NULL CHECK (nota >= 0 AND nota <= 5),
                            texto_review CLOB,  -- <--- Linha corrigida
                            data_criacao TIMESTAMP NOT NULL,
                            usuario_id UUID NOT NULL,
                            jogo_id UUID NOT NULL,
                            FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                            FOREIGN KEY (jogo_id) REFERENCES jogos(id)
);