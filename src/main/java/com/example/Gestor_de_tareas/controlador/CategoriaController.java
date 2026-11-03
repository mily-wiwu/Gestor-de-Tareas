package com.example.Gestor_de_tareas.controlador;

import com.example.Gestor_de_tareas.modelo.Categoria;
import com.example.Gestor_de_tareas.modelo.*;
import com.example.Gestor_de_tareas.servicio.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CategoriaController {
    private final CategoriaService categoriaService;
    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping("/gestor-de-tareas/categorias")
    public String verCategorias(Model model){
        List<Categoria> categorias = categoriaService.listarCategorias();
        System.out.println("Cantidad de categorías: " + categorias.size());
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @GetMapping("/gestor-de-tareas/categorias/nueva")
    public String mostrarFormulario() {
        return "nueva-categoria";
    }

    @PostMapping("/gestor-de-tareas/categorias/nueva")
    public String crearCategoria(@RequestParam String nombre, @RequestParam String descripcion) {
        System.out.println("ENTRÉ AL POST DE CATEGORÍAS");
        Categoria categoria = new Categoria(0, nombre, descripcion);

        boolean guardada = categoriaService.agregarCategoria(categoria);
        System.out.println("¿Se guardó?: " + guardada);
        System.out.println("Título enviado: " + categoria.getNombre());

        return "redirect:/gestor-de-tareas/categorias";
    }

    @PostMapping("/gestor-de-tareas/categorias/eliminar")
    public String eliminarCategoria(@RequestParam int id) {
        categoriaService.eliminarCategoria(id);

        return "redirect:/gestor-de-tareas/categorias";
    }
}
