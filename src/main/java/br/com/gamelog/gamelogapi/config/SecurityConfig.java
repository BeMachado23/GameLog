package br.com.gamelog.gamelogapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Bean para o Encoder de Senha
    //    Agora podemos injetar PasswordEncoder em qualquer serviço
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Bean para o Filtro de Segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita CSRF (Cross-Site Request Forgery) - comum em APIs stateless
                .csrf(csrf -> csrf.disable())

                // Define a política de sessão como STATELESS (não guarda estado/sessão no servidor)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorize -> authorize
                        // Permite que TODOS acessem o endpoint de criar usuário (cadastro)
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

                        // Permite que TODOS vejam a lista de jogos
                        .requestMatchers(HttpMethod.GET, "/jogos").permitAll()

                        // (Opcional) Permite ver detalhes de um jogo
                        .requestMatchers(HttpMethod.GET, "/jogos/**").permitAll()

                        // H2 Console (se estiver usando) - opcional para desenvolvimento
                        .requestMatchers("/h2-console/**").permitAll()

                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()

                        // Para TODAS as outras requisições, o usuário deve estar autenticado
                        .anyRequest().authenticated()
                )
                // Usa autenticação básica (usuário/senha via header)
                .httpBasic(httpBasic -> httpBasic.realmName("GameLog API"));

        // Necessário para o H2 Console funcionar (opcional)
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}