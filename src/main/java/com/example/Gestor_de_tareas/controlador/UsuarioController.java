package com.example.Gestor_de_tareas.controlador;

import com.example.Gestor_de_tareas.modelo.*;
import com.example.Gestor_de_tareas.servicio.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/gestor-de-tareas/usuarios")
    public String verUsuarios(Model model){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        System.out.println("Cantidad de usuarios: " + usuarios.size());
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    @GetMapping("/gestor-de-tareas/usuarios/nuevo")
    public String mostrarFormulario() {
        return "nuevo-usuario";
    }

    @PostMapping("/gestor-de-tareas/usuarios/nuevo")
    public String crearUsuario(@RequestParam String id, @RequestParam String nombre) {
        System.out.println("ENTRÉ AL POST DE USUARIOS");
        Usuario usuario = new Usuario(id, nombre);

        boolean guardada = usuarioService.agregarUsuario(usuario);
        System.out.println("¿Se guardó?: " + guardada);
        System.out.println("Nombre enviado: " + usuario.getNombre());

        return "redirect:/gestor-de-tareas/usuarios";
    }

    @PostMapping("/gestor-de-tareas/usuarios/eliminar")
    public String eliminarUsuario(@RequestParam String id) {
        usuarioService.eliminarUsuario(id);

        return "redirect:/gestor-de-tareas/usuarios";
    }
}
