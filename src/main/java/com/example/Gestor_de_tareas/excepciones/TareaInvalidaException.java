package com.example.Gestor_de_tareas.excepciones;

public class TareaInvalidaException extends RuntimeException {
    public TareaInvalidaException(String message) {
        super(message);
    }
}
