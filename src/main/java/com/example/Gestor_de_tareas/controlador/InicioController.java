package com.example.Gestor_de_tareas.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/gestor-de-tareas")
    public String inicio() {
        return "gestor-de-tareas";
    }
}