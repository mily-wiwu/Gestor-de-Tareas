package com.example.Gestor_de_tareas.servicio;

import com.example.Gestor_de_tareas.excepciones.CategoriaNoEncontradaException;
import com.example.Gestor_de_tareas.modelo.Categoria;
import com.example.Gestor_de_tareas.persistencia.dao.CategoriaDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaDAO categoriaDAO;

    public CategoriaService(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public boolean agregarCategoria(Categoria categoria) {
        return categoriaDAO.guardar(categoria);
    }

    public Categoria buscarCategoria(int id){
        return categoriaDAO.buscarPorId(id).orElseThrow(() ->
                new CategoriaNoEncontradaException("No existe la categoria con ID " + id));

    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.listar();
    }

}