package com.example.Gestor_de_tareas.excepciones;

public class DuplicadoException extends RuntimeException {
    public DuplicadoException(String message) {
        super(message);
    }
}
