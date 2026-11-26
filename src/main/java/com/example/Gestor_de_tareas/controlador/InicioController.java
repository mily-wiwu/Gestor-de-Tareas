package com.example.Gestor_de_tareas.controlador;

import com.example.Gestor_de_tareas.modelo.EstadisticasTareas;
import com.example.Gestor_de_tareas.servicio.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.Gestor_de_tareas.modelo.*;
import java.util.*;
import java.time.*;

@Controller
public class InicioController {
    private final TareaService tareaService;

    public InicioController(TareaService tareaService) {
        this.tareaService =tareaService;
    }

    @GetMapping("/gestor-de-tareas")
    public String verDashboard(Model model) {
        EstadisticasTareas estadisticas = tareaService.calcularSituacionTareas();
        LocalDate hoy = LocalDate.now();
        List<Tarea> tareas = tareaService.listarTareasCalendario();
        List<LocalDate> diasSemana = new ArrayList<>();
        Map<LocalDate, List<Tarea>> tareasPorDia = new LinkedHashMap<>();

        for (int i = 0; i < 7; i++) {
            LocalDate dia = hoy.plusDays(i);
            diasSemana.add(dia);
            tareasPorDia.put(dia, new ArrayList<>());
        }

        for (Tarea tarea : tareas) {
            if (tarea.getTipoAsignacionFecha() == TipoAsignacionFecha.FECHA_LIMITE) {
                LocalDate fechaLimite = tarea.getFechaLimite();

                if (fechaLimite == null) {
                    continue;
                }

                if (fechaLimite.isBefore(hoy)) {
                    tareasPorDia.get(hoy).add(tarea);
                    continue;
                }

                for (LocalDate fecha = hoy; !fecha.isAfter(fechaLimite); fecha = fecha.plusDays(1)) {
                    if (tareasPorDia.containsKey(fecha)) {
                        tareasPorDia.get(fecha).add(tarea);
                    }
                }

            } else if (tarea.getTipoAsignacionFecha() == TipoAsignacionFecha.DIA_ESPECIFICO) {
                LocalDate fechaProgramada = tarea.getFechaProgramada();

                if (fechaProgramada != null && tareasPorDia.containsKey(fechaProgramada)) {
                    tareasPorDia.get(fechaProgramada).add(tarea);
                }
            }
        }

        model.addAttribute("estadisticas", estadisticas);
        model.addAttribute("diasSemana", diasSemana);
        model.addAttribute("tareasPorDia", tareasPorDia);

        return "gestor-de-tareas";
    }
}