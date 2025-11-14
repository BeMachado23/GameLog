package br.com.gamelog.gamelogapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails { // <-- GRANDE MUDANÇA AQUI

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes;


    // --- MÉTODOS DO USERDETAILS (NOVOS) ---
    // Estes métodos são exigidos pelo Spring Security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Por enquanto, não estamos usando "Roles" (ex: ADMIN, USER)
        // Retornamos uma lista vazia.
        return List.of();
    }

    @Override
    public String getPassword() {
        // O Spring Security vai chamar este método para pegar a senha
        return this.senha;
    }

    @Override
    public String getUsername() {
        // O Spring Security vai usar o EMAIL como "username" para login
        return this.email;
    }

    // --- Métodos de controle de conta ---
    // Para esta SA, vamos deixar todos como 'true' (conta sempre ativa)

    @Override
    public boolean isAccountNonExpired() {
        return true; // A conta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // A conta nunca está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // As credenciais nunca expiram
    }

    @Override
    public boolean isEnabled() {
        return true; // A conta está sempre habilitada
    }
}