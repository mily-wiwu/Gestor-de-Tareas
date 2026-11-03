package com.example.Gestor_de_tareas.servicio;

import com.example.Gestor_de_tareas.excepciones.UsuarioNoEncontradoException;
import com.example.Gestor_de_tareas.modelo.*;
import com.example.Gestor_de_tareas.persistencia.dao.UsuarioDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean agregarUsuario(Usuario usuario) {
        return usuarioDAO.guardar(usuario);
    }

    public Usuario buscarUsuario(int id) {
        return usuarioDAO.buscarPorId(id).orElseThrow(() ->
                new UsuarioNoEncontradoException("No existe el usuario con ID " + id));
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }
}