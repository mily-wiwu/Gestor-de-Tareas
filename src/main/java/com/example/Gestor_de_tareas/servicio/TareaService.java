package com.example.Gestor_de_tareas.servicio;

import com.example.Gestor_de_tareas.persistencia.dao.TareaDAO;
import org.springframework.stereotype.Service;

@Service
public class TareaService {

    private final TareaDAO tareaDAO;

    public TareaService(TareaDAO tareaDAO) {
        this.tareaDAO = tareaDAO;
    }
}