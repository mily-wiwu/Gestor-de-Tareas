
package com.example.Gestor_de_tareas.servicio;

import com.example.Gestor_de_tareas.modelo.*;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ExportacionService {

    private final TareaService tareaService;
    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;

    public ExportacionService(
            TareaService tareaService,
            UsuarioService usuarioService,
            CategoriaService categoriaService) {

        this.tareaService = tareaService;
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
    }

    public void exportarTareas() throws IOException {
        List<Tarea> tareas = tareaService.listarTareas();

        Path ruta = Paths.get("exportaciones", "tareas.csv");

        Files.createDirectories(ruta.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(ruta)) {
            writer.write("ID,Titulo,Descripcion,Prioridad,Estado,FechaLimite");
            writer.newLine();

            for (Tarea tarea : tareas) {
                writer.write(
                        tarea.getId() + "," +
                                tarea.getTitulo() + "," +
                                tarea.getDescripcion() + "," +
                                tarea.getPrioridad() + "," +
                                tarea.getEstado() + "," +
                                tarea.getFechaLimite()
                );
                writer.newLine();
            }
        }
    }

    public void exportarUsuarios() throws IOException {
        List<Usuario> usuarios = usuarioService.listarUsuarios();

        Path ruta = Paths.get("exportaciones", "usuarios.csv");

        Files.createDirectories(ruta.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(ruta)) {
            writer.write("ID,Nombre");
            writer.newLine();

            for (Usuario usuario : usuarios) {
                writer.write(
                        usuario.getId() + "," +
                                usuario.getNombre() + ","
                );
                writer.newLine();
            }
        }
    }

    public void exportarCategorias() throws IOException {
        List<Categoria> categorias = categoriaService.listarCategorias();

        Path ruta = Paths.get("exportaciones", "categorias.csv");

        Files.createDirectories(ruta.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(ruta)) {
            writer.write("ID,Nombre,Descripcion");
            writer.newLine();

            for (Categoria categoria : categorias) {
                writer.write(
                        categoria.getId() + "," +
                                categoria.getNombre() + "," +
                                categoria.getDescripcion() + ","
                );
                writer.newLine();
            }
        }
    }

    public void exportarTodo() throws IOException {
        exportarTareas();
        exportarUsuarios();
        exportarCategorias();
    }
}
