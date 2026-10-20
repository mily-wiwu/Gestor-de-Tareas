package com.example.Gestor_de_tareas.excepciones;

public class TareaNoEncontradaException extends RuntimeException {
    public TareaNoEncontradaException(String message) {
        super(message);
    }
}
