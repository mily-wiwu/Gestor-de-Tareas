package com.example.Gestor_de_tareas.servicio;

import com.example.Gestor_de_tareas.persistencia.dao.CategoriaDAO;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaDAO categoriaDAO;

    public CategoriaService(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }
}