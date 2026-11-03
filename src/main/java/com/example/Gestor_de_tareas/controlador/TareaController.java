package com.example.Gestor_de_tareas.controlador;
import com.example.Gestor_de_tareas.servicio.CategoriaService;
import com.example.Gestor_de_tareas.servicio.TareaService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.Gestor_de_tareas.modelo.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TareaController {
    private final TareaService tareaService;
    private final CategoriaService categoriaService;

    public TareaController(TareaService tareaService, CategoriaService categoriaService){
        this.tareaService = tareaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/gestor-de-tareas/tareas")
    public String verTarea(Model model) {
        List<Tarea> tareas = tareaService.listarTareas();
        model.addAttribute("tareas", tareas);
        return "tareas";
    }

    @GetMapping("/gestor-de-tareas/tareas/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "nueva-tarea";
    }



    @PostMapping("/gestor-de-tareas/tareas/nueva")
    public String crearTarea(@RequestParam String titulo, @RequestParam String descripcion, @RequestParam int idCategoria, @RequestParam LocalDate fechaLimite, @RequestParam Prioridad prioridad) {
        Categoria categoria = categoriaService.buscarCategoria(idCategoria);
        LocalDate fechaCreacion = LocalDate.now();
        EstadoTarea estado = EstadoTarea.PENDIENTE;

        Tarea tarea = new Tarea(0, titulo, descripcion, fechaCreacion, fechaLimite, estado, prioridad, categoria, null);

        boolean guardada = tareaService.agregarTarea(tarea);
        System.out.println("¿Se guardó?: " + guardada);
        System.out.println("Título enviado: " + tarea.getTitulo());

        return "redirect:/gestor-de-tareas/tareas";
    }

    @PostMapping("/gestor-de-tareas/tareas/eliminar")
    public String eliminarTarea(@RequestParam int id) {
        tareaService.eliminarTarea(id);

        return "redirect:/gestor-de-tareas/tareas";
    }
}
