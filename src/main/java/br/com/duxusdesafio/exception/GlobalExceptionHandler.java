package br.com.duxusdesafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException e){
        Map<String, String> erros = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> erros.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleIntegranteExistente(ResponseStatusException e){
        Map<String, String> erros = new HashMap<>();
        erros.put("message", e.getReason());
        return ResponseEntity.status(HttpStatus.valueOf(e.getRawStatusCode())).body(erros);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleInvalidLocalDate(HttpMessageNotReadableException e){
        Map<String, String> erro = new HashMap<>();
        erro.put("message", "Formato de dado inválido. Por favor, revise os campos");

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){
        Map<String, String> erro = new HashMap<>();
        erro.put("message", "Parâmetro " + ex.getName() + " invalido.");

        return ResponseEntity.badRequest().body(erro);
    }
}
