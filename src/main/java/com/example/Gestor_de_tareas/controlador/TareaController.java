    package com.example.Gestor_de_tareas.controlador;
    import com.example.Gestor_de_tareas.servicio.*;
    import org.springframework.ui.Model;
    import com.example.Gestor_de_tareas.persistencia.*;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import com.example.Gestor_de_tareas.modelo.*;
    import java.time.LocalDate;
    import java.util.List;

    @Controller
    public class TareaController {
        private final TareaService tareaService;
        private final CategoriaService categoriaService;
        private final UsuarioService usuarioService;

        public TareaController(TareaService tareaService, CategoriaService categoriaService, UsuarioService usuarioService){
            this.tareaService = tareaService;
            this.categoriaService = categoriaService;
            this.usuarioService = usuarioService;
        }

        @GetMapping("/gestor-de-tareas/tareas")
        public String verTarea(@RequestParam(required = false) String titulo, @RequestParam(required = false) Integer idCategoria, @RequestParam(required = false) Prioridad prioridad, @RequestParam(required = false) EstadoTarea estado, Model model) {
            List<Tarea> tareas = tareaService.buscarTareas(titulo, idCategoria, prioridad, estado);

            model.addAttribute("tareas", tareas);
            model.addAttribute("categorias", categoriaService.listarCategorias());
            model.addAttribute("usuarios", usuarioService.listarUsuarios());
            model.addAttribute("titulo", titulo);
            model.addAttribute("idCategoria", idCategoria);
            model.addAttribute("prioridadSeleccionada", prioridad);
            model.addAttribute("estadoSeleccionado", estado);

            return "tareas";
        }

        @GetMapping("/gestor-de-tareas/tareas/nueva")
        public String mostrarFormulario(Model model) {
            model.addAttribute("categorias", categoriaService.listarCategorias());
            model.addAttribute("usuarios", usuarioService.listarUsuarios());
            return "nueva-tarea";
        }



        @PostMapping("/gestor-de-tareas/tareas/nueva")
        public String crearTarea(@RequestParam String titulo, @RequestParam String descripcion, @RequestParam int idCategoria, @RequestParam LocalDate fechaLimite, @RequestParam Prioridad prioridad, @RequestParam(required = false) String usuarioId) {
            Categoria categoria = categoriaService.buscarCategoria(idCategoria);
            LocalDate fechaCreacion = LocalDate.now();
            EstadoTarea estado = EstadoTarea.PENDIENTE;
            Usuario usuario = null;
            if (usuarioId != null && !usuarioId.isBlank()) {
                usuario = usuarioService.buscarUsuario(usuarioId);
            }

            Tarea tarea = new Tarea(0, titulo, descripcion, fechaCreacion, fechaLimite, estado, prioridad, categoria, usuario);

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

        @GetMapping("/gestor-de-tareas/tareas/editar/{id}")
        public String mostrarFormularioEditar(@PathVariable int id, Model model) {
            Tarea tarea = tareaService.buscarTarea(id);
            model.addAttribute("tarea", tarea);
            model.addAttribute("categorias", categoriaService.listarCategorias());
            return "editar-tarea";
        }

        @PostMapping("/gestor-de-tareas/tareas/editar")
        public String editarTarea(@RequestParam int id, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam LocalDate fechaLimite, @RequestParam Prioridad prioridad, @RequestParam int idCategoria) {
            Tarea original = tareaService.buscarTarea(id);
            Categoria categoria = categoriaService.buscarCategoria(idCategoria);

            Tarea tareaActualizada = new Tarea(original.getId(), titulo, descripcion, original.getFechaCreacion(), fechaLimite, original.getEstado(), prioridad, categoria, original.getUsuario());
            tareaService.modificarTarea(tareaActualizada);

            return "redirect:/gestor-de-tareas/tareas";
        }

        @PostMapping("/gestor-de-tareas/tareas/estado")
        public String cambiarEstado(@RequestParam int id, @RequestParam EstadoTarea estado) {
            tareaService.cambiarEstado(id, estado);
            return "redirect:/gestor-de-tareas/tareas";
        }

        @PostMapping("/gestor-de-tareas/tareas/usuario")
        public String asignarUsuario(@RequestParam int id, @RequestParam(required = false) String usuarioId) {
            Usuario usuario = null;

            if (usuarioId != null && !usuarioId.isBlank()) {
                usuario = usuarioService.buscarUsuario(usuarioId);
            }

            tareaService.asignarUsuario(id, usuario);

            return "redirect:/gestor-de-tareas/tareas";
        }
    }
