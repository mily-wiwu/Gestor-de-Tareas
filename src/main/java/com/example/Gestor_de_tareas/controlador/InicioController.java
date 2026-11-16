package com.example.Gestor_de_tareas.controlador;

import com.example.Gestor_de_tareas.modelo.EstadisticasTareas;
import com.example.Gestor_de_tareas.modelo.EstadoTarea;
import com.example.Gestor_de_tareas.modelo.SituacionTemporal;
import com.example.Gestor_de_tareas.servicio.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    private final TareaService tareaService;

    public InicioController(TareaService tareaService) {
        this.tareaService =tareaService;
    }

    @GetMapping("/gestor-de-tareas")
    public String verDashboard(Model model) {
        EstadisticasTareas estadisticas = tareaService.calcularSituacionTareas();
        model.addAttribute("estadisticas", estadisticas);
        return "gestor-de-tareas";
    }
}