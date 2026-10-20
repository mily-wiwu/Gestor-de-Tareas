package com.example.Gestor_de_tareas.servicio;

import com.example.Gestor_de_tareas.persistencia.dao.UsuarioDAO;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
}