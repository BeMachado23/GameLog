package br.com.gamelog.gamelogapi.exception;
//recomendacao da IA

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Quando esta exceção for lançada, o Spring saberá que deve retornar um 400 Bad Request
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String message) {
        super(message);
    }
}